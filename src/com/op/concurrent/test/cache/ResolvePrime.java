package com.op.concurrent.test.cache;

/**
 * Created by Administrator on 2017/9/22.
 */
public class ResolvePrime implements Computable<Integer, String> {


    @Override
    public String compute(Integer num) {
        // �������ַ���������������������ַ�
        StringBuffer sb = new StringBuffer(num + "=");
        // ������С����
        int i = 2;
        // ����շת�����
        while (i <= num) {
            // ��num ������ i ����i ��num ��һ������
            if (num % i == 0) {
                // ��i �����sb �� ������� *
                sb.append(i + "*");
                // ͬʱ�� num����i ��ֵ���� num
                num = num / i;
                // ��i������Ϊ2
                i = 2;
            } else {
                // ���޷���������i ����
                i++;
            }
        }
        // ȥ���ַ��������������һ��*�����������
        return sb.toString().substring(0, sb.toString().length() - 1);
    }
}
