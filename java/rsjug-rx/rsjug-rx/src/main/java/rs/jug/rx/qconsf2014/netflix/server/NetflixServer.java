package rs.jug.rx.qconsf2014.netflix.server;

import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import rs.jug.rx.qconsf2014.netflix.ReactiveHttpRequestHandler;

import java.util.Map;

public final class NetflixServer {

	static final int DEFAULT_PORT = 8090;

	private final int port;
	
	private ReactiveHttpRequestHandler handler = new ReactiveHttpRequestHandler();

	public NetflixServer(int port) {
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
		new NetflixServer(DEFAULT_PORT).createServer().startAndWait();
	}
}