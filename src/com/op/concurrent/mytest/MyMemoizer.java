package com.op.concurrent.mytest;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2017/9/27.
 */
public class MyMemoizer<K,V> implements MyComputable<K,V>{
    private final ConcurrentMap<K,Future<V>> cache = new ConcurrentHashMap<>();
    private final MyComputable<K,V> computable;

    public MyMemoizer(MyComputable<K,V> computable){
        this.computable = computable;
    }

    @Override
    public V compute(final K k) {
        while (true) {
            Future<V> future = cache.get(k);
            if (future == null) {
                FutureTask<V> f = new FutureTask(new Callable() {
                    @Override
                    public V call() throws Exception {
                        return computable.compute(k);
                    }
                });
                future = cache.putIfAbsent(k, f);
                if (future == null) {
                    f.run();
                    future = f;
                }
            }
            try {
                return future.get();
            } catch (InterruptedException e) {
                cache.remove(k);
                e.printStackTrace();
            } catch (ExecutionException e) {
                cache.remove(k);
                e.printStackTrace();
            }
        }
    }

}
