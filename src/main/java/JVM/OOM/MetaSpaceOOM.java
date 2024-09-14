//package JVM.OOM;
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//import java.lang.reflect.Method;
//
///**
// * JVM参数
// * -XX:MetaspaceSize = 8m  -XX:MaxMetaspaceSize = 8m
// *
// * java8及之后的版本使用Metaspace来替代永久代。
// * Metaspace是方法区在HotSpot中的实现，它与持久代最大的区别在于Metaspace并不在虚拟机内存中而是使用本地内存。
// * 存储以下信息：
// * 虚拟机加载的类信息；
// * 常量池；
// * 静态变量；
// * 即时编译后的代码。
// */
//public class MetaSpaceOOM {
//    static class  OOMTest{
//
//    }
//
//    public static void main(String[] args) {
//        int i = 0;
//        try {
//        while (true){
//            i++;
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(OOMTest.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback({
//                    @Override
//                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                        return methodProxy.invokeSuper(o,args);
//                    }
//            });
//            enhancer.create();
//        }
//        }catch (Exception e){
//            System.out.println("**********多少次后发生异常："+i);
//            e.printStackTrace();
//        }
//    }
//}
