package com.op.concurrent.test.cache;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/9/21.
 */
public class Memoizer <K,V> implements Computable<K,V>{
    private final ConcurrentMap<K, Future<V>> cache = new ConcurrentHashMap<K, Future<V>>();
    private Computable computable = null;
    public Memoizer(Computable computable){
        this.computable = computable;
    }
    @Override
    public V compute(final K k) {
        Future<V> future = cache.get(k);
        if(future==null) {
            FutureTask ft = new FutureTask(new Callable() {
                @Override
                public Object call() throws Exception {
                    return computable.compute(k);
                }
            });
/*            future = ft;
            cache.put(k, ft);
            ft.run();*/
            future = cache.putIfAbsent(k, ft);
            if(future==null) {
                future = ft;
                ft.run();
            }
        }
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
