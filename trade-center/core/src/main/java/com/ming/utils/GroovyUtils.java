package com.ming.utils;

/**
 * groovy shell 脚本 工具类
 *
 * @author ming
 * @date 2017-11-08 10:32
 */
public class GroovyUtils {

    /* *//**
     * GROOVY_SHELL 引擎
     *
     * @author onlyedu.onlyedu.onlyedu
     * @date 2017-11-08 10:34
     *//*
    private static final GroovyShell GROOVY_SHELL = new GroovyShell();
    *//**
     * groovy classloader
     *
     * @author onlyedu.onlyedu.onlyedu
     * @date 2017-11-08 17:11
     *//*
    private static final GroovyClassLoader GROOVY_CLASS_LOADER = new GroovyClassLoader(GroovyUtils.class.getClassLoader());
    *//**
     * groovy script 注册的bean
     *
     * @author onlyedu.onlyedu.onlyedu
     * @date 2017-11-10 15:54
     *//*
    public static volatile Map<String, Class<?>> GROOVY_SCRIPT_BEAN_MAP = Maps.newConcurrentMap();

    @SuppressWarnings("unchecked")
    public static BaseProxyJob newGroovyScriptJobObject(String groovyScriptContent) throws IllegalAccessException, InstantiationException {
        Class obj = GROOVY_CLASS_LOADER.parseClass(groovyScriptContent);
        // 当是继承 qz的job接口的时候
        if (isInterface(obj, "org.base.Job")) {
            return (BaseProxyJob) obj.newInstance();
        }
        throw new RuntimeException("不是继承 org.base.job接口的无法实例化为 qz的job");
    }


    @SuppressWarnings("unchecked")
    public static BaseProxyJob newInsta() throws IllegalAccessException, InstantiationException {
        Long now = System.currentTimeMillis();
        String script = "\n" +
                "package com.onlyedu.onlyedu.onlyedu.core.base;\n" +
                "\n" +
                "class TestGroovyJob extends BaseProxyJob{\n" +
                "    @Override\n" +
                "    void execute() {\n" +
                "        println \"testGroovyJob\"\n" +
                "    }\n" +
                "}\n";

        Class obj = GROOVY_CLASS_LOADER.parseClass(script);
        System.out.println(isInterface(obj, "org.base.Job"));
        System.out.println(obj);

        System.out.println(System.currentTimeMillis() - now);
        return (BaseProxyJob) obj.newInstance();
    }

    private static boolean isInterface(Class obj, String className) {
        List<Class<?>> interfaceList = Lists.newArrayList(obj.getInterfaces());
        Set<String> stringSet = interfaceList.stream().map(Class::getName).collect(Collectors.toSet());
        return stringSet.contains(className);
    }

    *//**
     * 执行 groovy 脚本  返回JSON 字符串
     *
     * @author onlyedu.onlyedu.onlyedu
     * @date 2017-11-08 10:39
     *//*
    public String run(String script) {
        return JSON.toJSONString(GROOVY_SHELL.parse(script).run());
    }*/
}
