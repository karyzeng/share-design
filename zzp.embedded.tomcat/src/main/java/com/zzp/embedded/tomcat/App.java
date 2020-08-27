package com.zzp.embedded.tomcat;

import com.zzp.embedded.tomcat.filters.TestFilter;
import com.zzp.embedded.tomcat.listeners.TestListener;
import com.zzp.embedded.tomcat.servlets.TestServlet;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.EmptyResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @Description 嵌入式tomcat启动类
 * @Author karyzeng
 * @since 2020.08.07
 **/
public class App {

    private static final String WORK_HOME = System.getProperty("user.dir");

    public static void main(String[] args) {
        try {
            String contextPath = "";
            Path tempBaseDir = Files.createTempDirectory("tomcat-temp-base-dir");// 创建临时目录作为tomcat的基础目录
            Path tempDocDir = Files.createTempDirectory("tomcat-temp-doc-dir");// 创建临时目录作为应用文档资源的目录

            Tomcat tomcat = new Tomcat();
            Connector connector = new Connector();
            connector.setPort(8080);// 设置绑定端口
            tomcat.getService().addConnector(connector);// 或者使用tomcat.setConnector(connector);将connector添加到tomcat的第一个service中
            tomcat.getHost().setAutoDeploy(false);// 当前host不需要设置自动部署
            tomcat.setBaseDir(tempBaseDir.toFile().getAbsolutePath());

            StandardContext context = (StandardContext) tomcat.addWebapp(contextPath, tempDocDir.toFile().getAbsolutePath());//创建应用上下文
            context.setParentClassLoader(App.class.getClassLoader());
            context.setUseRelativeRedirects(false);
            configureContextParameters(context);
            configureResources(context);
            configureServlets(contextPath, tomcat, context);
            configureFilters(context);

            //tomcat 启动jar扫描设置为跳过所有，避免与框架结合出现 jar file not found exception
            System.setProperty("tomcat.util.scan.StandardJarScanFilter.jarsToSkip", "\\,*");

            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void configureContextParameters(StandardContext context) {
        context.addParameter("param1", "1");
        context.addParameter("param2", "2");
    }

    private static void configureResources(StandardContext context) {
        File classesDir = new File(WORK_HOME, "target/classes");
        File jarDir = new File(WORK_HOME, "lib");
        WebResourceRoot resources = new StandardRoot(context);
        if (classesDir.exists()) {
            resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", classesDir.getAbsolutePath(), "/"));
            System.out.println("Resources added: [classes]");
        } else if (jarDir.exists()) {
            resources.addJarResources(new DirResourceSet(resources, "/WEB-INF/lib", jarDir.getAbsolutePath(), "/"));
            System.out.println("Resources added: [jar]");
        } else {
            resources.addPreResources(new EmptyResourceSet(resources));
            System.out.println("Resources added: [empty]");
        }
        context.setResources(resources);
    }

    private static void configureServlets(String contextPath, Tomcat tomcat, StandardContext context) {
//        // -- Writing1:
//        {
//            tomcat.addServlet(contextPath, "test1", TestServlet.class.getName());
//            context.addServletMappingDecoded("/test1/*", "test1");//注意不要忘记设置Servlet路径映射
//        }

        // -- Writing2:
        {
            Wrapper wrapper = context.createWrapper();
            wrapper.setName("test");
            wrapper.setServletClass(TestServlet.class.getName());
            context.addChild(wrapper);
            context.addServletMappingDecoded("/test/*", "test");//注意不要忘记设置Servlet路径映射
        }
    }

    private static void configureFilters(StandardContext context) {
        FilterDef filterDef = new FilterDef();//Filter定义
        filterDef.setFilterName("test-filter");
        filterDef.setFilterClass(TestFilter.class.getName());
        filterDef.addInitParameter("name", "test-filter");
        context.addFilterDef(filterDef);
        FilterMap filterMap = new FilterMap();//Filter路径映射
        filterMap.setFilterName("test-filter");
        filterMap.addURLPattern("/*");
        context.addFilterMap(filterMap);
    }

    private static void configureListeners(StandardContext context) {
        context.addApplicationEventListener(new TestListener());
    }

}
