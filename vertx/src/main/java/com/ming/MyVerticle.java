package com.ming;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class MyVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.createHttpServer().requestHandler(httpServerRequest -> {
            httpServerRequest.response().putHeader("content-type", "text/plain")
                    .end("nihao  vert.x");

        }).listen(8080);
    }
}
