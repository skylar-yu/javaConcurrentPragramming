package com.op.concurrent.test.cache;

/**
 * Created by Administrator on 2017/9/22.
 */
public class ResolvePrime implements Computable<Integer, String> {


    @Override
    public String compute(Integer num) {
        // 定义结果字符串缓存对象，用来保存结果字符
        StringBuffer sb = new StringBuffer(num + "=");
        // 定义最小素数
        int i = 2;
        // 进行辗转相除法
        while (i <= num) {
            // 若num 能整除 i ，则i 是num 的一个因数
            if (num % i == 0) {
                // 将i 保存进sb 且 后面接上 *
                sb.append(i + "*");
                // 同时将 num除以i 的值赋给 num
                num = num / i;
                // 将i重新置为2
                i = 2;
            } else {
                // 若无法整除，则i 自增
                i++;
            }
        }
        // 去除字符串缓存对象最后的一个*，将结果返回
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
