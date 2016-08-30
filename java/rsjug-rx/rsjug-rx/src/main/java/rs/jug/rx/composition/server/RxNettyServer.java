package rs.jug.rx.composition.server;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import rs.jug.rx.composition.SeriesHttpHandler;
import rs.jug.rx.qconsf2014.netflix.ReactiveHttpRequestHandler;

import java.util.Map;

public final class RxNettyServer {

	static final int DEFAULT_PORT = 8090;

	private final int port;
	
	private SeriesHttpHandler handler = new SeriesHttpHandler();

	public RxNettyServer(int port) {
		this.port = port;
	}

	public HttpServer<ByteBuf, ByteBuf> createServer() {
		return RxNetty.createHttpServer(port, handler::handle);
	}

	public void printRequestHeader(HttpServerRequest<ByteBuf> request) {
		System.out.println("New request received");
		System.out.println(request.getHttpMethod() + " " + request.getUri() + ' ' + request.getHttpVersion());
		for (Map.Entry<String, String> header : request.getHeaders().entries()) {
			System.out.println(header.getKey() + ": " + header.getValue());
		}
	}

	public static void main(final String[] args) {
		System.out.println("HTTP hello world server starting ...");
		new RxNettyServer(DEFAULT_PORT).createServer().startAndWait();
	}
}