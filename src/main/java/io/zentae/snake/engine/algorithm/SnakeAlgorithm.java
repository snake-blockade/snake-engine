package io.zentae.snake.engine.algorithm;

import io.zentae.snake.engine.controller.game.GameController;
import io.zentae.snake.engine.entity.Location;
import io.zentae.snake.engine.entity.arena.Arena;
import io.zentae.snake.engine.entity.fruit.Fruit;
import io.zentae.snake.engine.entity.snake.Snake;
import io.zentae.snake.engine.movement.Movement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnakeAlgorithm extends Algorithm<Movement, Path> {

    public SnakeAlgorithm() {
        super(10);
    }

    @Override
    public Movement process(GameController game) {
        // retrieve the arena.
        Arena arena = game.getGame().getArena();
        // retrieve the snake.
        Snake snake = game.getCurrentPlayer().getController().getSnake();
        // do the maths.
        // return the play.
        return next(game, translate(arena), translateSnake(snake), null, 0).getMovement();
    }

    @Override
    protected Path next(Object... objects) {
        // check if there's not enough args.
        if(objects.length < 5)
            // return no movement.
            return new Path(Movement.NONE);
        // retrieve the arena.
        GameController game;
        char[][] arenaMatrix;
        int[][] snake;
        int[] move;
        int layer;
        try {
            game = (GameController) objects[0];
            arenaMatrix = (char[][]) objects[1];
            snake = (int[][]) objects[2];
            move = (int[]) objects[3];
            layer = (int) objects[4];
        } catch (ClassCastException exception) {
            return new Path(Movement.NONE);
        }
        // retrieve the arena.
        Arena arena = game.getGame().getArena();
        // check if the move is valid.
        if(!isValid(arenaMatrix, move) || hasSelfCollided(snake) || layer >= getLayer()) {
            return new Path(Movement.NONE);
        }
        // increment the layer.
        layer += 1;
        // our path buffer.
        List<Path> pathBuffer = new ArrayList<>();
        // loop through each movement.
        for(Movement movement : Movement.values()) {
            // create a new path.
            Path path = new Path(movement);
            // set the default weight.
            path.setWeight(1);
            // add it to the buffer.
            pathBuffer.add(path);
        }
        // loop through each path.
        for(Path path : pathBuffer) {
            // retrieve the movement.
            Movement movement = path.getMovement();
            // check if there's a planned movement.
            if(move != null) {
                // loop through each fruit in the arena.
                for(Fruit fruit : arena.getObjects(Fruit.class)) {
                    // loop through each fruit location.
                    for(Location location : fruit.getSegments()) {
                        // do the math for the distance.
                        double distance = Math.abs(location.getX() - move[0]) + Math.abs(location.getY() - move[1]);
                        // update the weight.
                        path.setWeight(path.getWeight() + Math.exp(Math.exp(-distance) / (2D * Math.sqrt(0.0001D))));
                    }
                }
            }
            // prepare a snake copy.
            int[][] snakeCopy;
            // prepare coordinate.
            int[] coordinate;
            // make a copy of the snake.
            snakeCopy = Arrays.copyOf(snake, snake.length + 1);
            // calculate new coordinates for the head.
            coordinate = new int[]{snake[snake.length - 1][0] + movement.getX(),
                    snake[snake.length - 1][1] + movement.getY()};
            // add the new coordinates.
            snakeCopy[snakeCopy.length - 1] = coordinate;
            // check if the snake must grow.
            if((game.getGame().getLap() + layer) % game.getGame().getLapGrow() == 0) {
                // make a copy of the snake.
                snakeCopy = Arrays.copyOf(snake, snake.length + 1);
                // calculate new coordinates for the head.
                coordinate = new int[]{snake[snake.length - 1][0] + movement.getX(),
                        snake[snake.length - 1][1] + movement.getY()};
                // add the new coordinates.
                snakeCopy[snakeCopy.length - 1] = coordinate;
            } else {
                // make a copy of the snake of the same size.
                snakeCopy = Arrays.copyOf(snake, snake.length);
                // calculate new coordinates for the head.
                coordinate = new int[]{snake[snake.length - 1][0] + movement.getX(),
                        snake[snake.length - 1][1] + movement.getY()};
                // loop through each body part.
                for(int i = 0; i < snakeCopy.length; i++) {
                    if(i + 1 >= snakeCopy.length) {
                        snakeCopy[i] = coordinate;
                    } else {
                        // update the position.
                        snakeCopy[i] = snakeCopy[i + 1];
                    }
                }
            }
            // add the sub-paths into the current path.
            path.getPaths().add(next(game, arenaMatrix, snakeCopy, coordinate, layer));
        }
        // initialize the best path.
        Path bestPath = null;
        // TODO DISTANCE AVEC TETE DU JOUEUR

        double bestWeight = Double.MIN_VALUE;
        // loop through each path.
        for(Path path : pathBuffer) {
            if(bestPath == null) {
                bestPath = path;
                bestWeight = path.getAllWeight();
            } else {
                if(bestWeight < path.getAllWeight()) {
                    bestPath = path;
                    bestWeight = path.getAllWeight();
                }
            }
        }
        //System.out.println("Weight:" + bestPath.getAllWeight());
        //System.out.println("----------");
        return bestPath;
    }

    private int[][] translateSnake(Snake snake) {
        // prepare the matrix.
        int[][] matrix = new int[snake.getBody().size()][2];
        for(int i = 0; i < snake.getBody().size(); i++) {
            Location location = snake.getBody().get((snake.getBody().size() - 1) - i);
            matrix[i] = new int[]{location.getX(), location.getY()};
        }
        return matrix;
    }

    private char[][] translate(Arena arena) {
        // prepare the matrix.
        char[][] matrix = new char[arena.getWidth()][arena.getLength()];
        // fill array with taken positions.
        for (char[] bytes : matrix) {
            Arrays.fill(bytes, 'X');
        }
        // free up positions.
        for(Location location : arena.getFreeSegments()) {
            matrix[location.getY()][location.getX()] = 'O';
        }
        // free up fruits.
        for(Fruit fruit : arena.getObjects(Fruit.class)) {
            for(Location location : fruit.getSegments()) {
                matrix[location.getY()][location.getX()] = 'F';
            }
        }
        return matrix;
    }

    private boolean hasSelfCollided(int[][] body) {
        int[] head = body[body.length - 1];
        for(int i = body.length - 2; 0 <= i; i--) {
            if(body[i][0] == head[0] && body[i][1] == head[1])
                return true;
        }

        return false;
    }

    private boolean isValid(char[][] arena, int[] coordinate) {
        if(coordinate == null)
            return true;
        if(coordinate[0] < 0 || coordinate[0] > arena[0].length - 1
                || coordinate[1] < 0 || coordinate[1] > arena.length - 1)
            return false;
        return arena[coordinate[1]][coordinate[0]] != 'X';
    }
}
