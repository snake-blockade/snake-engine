package io.zentae.snake.engine.io;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

/**
 * This class comes from the project : https://git.lacl.fr/cervelle/padiflac by Julien Cervelle
 * The field names have been modified in order to respect java's naming convention.
 */
public class Channel<T> {
	private final String channelName;
	private final Queue<T> events = new ArrayDeque<>();
	private int id = 0;
	private static final String ADDRESS = "https://prog-reseau-m1.lacl.fr/padiflac/";

	private static final String NONCE =
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final Random RANDOM = new Random();

	private String genNonce() {
		char[] sb = new char[64];
		for(int i=0; i<64;i++) {
			sb[i] = NONCE.charAt(RANDOM.nextInt(62));
		}
		return new String(sb);
	}

	public Channel(String channelName) {
		this.channelName = channelName;
	}

	private byte[] serializeToString(T o) throws IOException {
		if (o instanceof String)
			return ((String)o).getBytes(StandardCharsets.UTF_8) ;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(stream);
		oos.writeObject(o);
		oos.close();
		return stream.toByteArray();
	}

	@SuppressWarnings("unchecked")
	private T unserializeFromString(byte[] s) throws IOException, ClassNotFoundException {
		try {
			ByteArrayInputStream stream = new ByteArrayInputStream(s);
			ObjectInputStream ois = new ObjectInputStream(stream);
			T readObj = (T) ois.readObject();
			ois.close();
			return readObj;
		}catch(StreamCorruptedException | EOFException e) {
			return (T) new String(s);
		}
	}

	private void parseEvent(byte[] bb, int i) throws ClassNotFoundException, IOException {
		StringBuilder sb = new StringBuilder();
		while(i<bb.length) {
			byte b = bb[i];
			if(b=='|')break;
			sb.append((char)b);
			i++;
		}
		i++;
		if(i<bb.length) {
			int size = Integer.parseInt(sb.toString());
			byte[] buff = java.util.Arrays.copyOfRange(bb, i, i+size);
			T t = unserializeFromString(buff);
			events.add(t);
			parseEvent(bb, i+size);
		}else {
			id =Integer.parseInt(sb.toString());
		}
	}

	private void connect() throws IOException, ClassNotFoundException {
		String id2 = "";
		if (id>0) {
			id2 = "?id="+id;
		}
		URL u;
		try {
			u = new URL(ADDRESS + channelName +id2);
			URLConnection uc = u.openConnection();
			InputStream is = uc.getInputStream();
			parseEvent(is.readAllBytes(),0);
		} catch (MalformedURLException e) {
			throw new AssertionError(e);
		}
	}

	public T getNext() throws IOException, ClassNotFoundException {
		while (events.isEmpty()) {
			connect();
		}
		return events.poll();
	}


	public void send(T s) throws IOException, ClassNotFoundException {
		//Make sure connect have been called to enforce that the channel contain type T.
		if(id==0)
			connect();
		URL u;
		try {
			String non = genNonce();
			u = new URL(ADDRESS + channelName +"?nonce="+non);
			HttpsURLConnection uc = (HttpsURLConnection)u.openConnection();
			uc.setRequestMethod("POST");
			uc.setDoOutput(true);
			OutputStream os = uc.getOutputStream();
			os.write(serializeToString(s));
			os.close();
			uc.connect();
			byte[] result = uc.getInputStream().readAllBytes();
			// result should be ['O','K'] = [79, 75]
			if (!Arrays.equals(result,OK)) {
				throw new IOException("Unexpected answer from server");
			}
		} catch (MalformedURLException e) {
			throw new AssertionError(e);
		}
	}
	private static final byte[] OK = {'O','K'};

	private static String escape(String s) {
		StringBuilder str = new StringBuilder();
		var c = s.codePoints();
		c.forEachOrdered(i -> {
			if (Character.isISOControl(i)) {
				str.append("\\").append(String.format("%03o",i));
			} else {
				str.append(Character.toString(i));
			}
		});
		return str.toString();
	}

	public static void testString() throws IOException, ClassNotFoundException {
		Channel<String> c = new Channel<>("ChatTest");
		for(;;) {
			System.out.println(escape(c.getNext()));
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		//testObject();
		testString();
	}


}

