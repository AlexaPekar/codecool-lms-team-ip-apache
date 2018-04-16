package com.codecool.lms.listener;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class WebappContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /*
        System.out.println("This method is invoked once when the webapp gets deployed.");

        //Initialize users for demo
        User feri = UserServiceImpl.getUserService().createUser("ferenc@gmail.com", "Ferenc Hajnal", "12345678", "Student");
        User balint = UserServiceImpl.getUserService().createUser("balint@gmail.com", "Balint Csizmadia", "12345678", "Student");
        User bence = UserServiceImpl.getUserService().createUser("bence@gmail.com", "Bence Farago", "12345678", "Student");
        User alexa = UserServiceImpl.getUserService().createUser("alexa@gmail.com", "Alexa Pekar", "12345678", "Student");
        User pal = UserServiceImpl.getUserService().createUser("pal@codecool.com", "Pal Monoczki", "12345678", "Mentor");
        User robert = UserServiceImpl.getUserService().createUser("robert@codecool.com", "Robert Kohanyi", "12345678", "Mentor");
        User imre = UserServiceImpl.getUserService().createUser("imre@codecool.com", "Imre Lindi", "12345678", "Mentor");
        try {
            UserServiceImpl.getUserService().register(feri);
            UserServiceImpl.getUserService().register(balint);
            UserServiceImpl.getUserService().register(bence);
            UserServiceImpl.getUserService().register(alexa);
            UserServiceImpl.getUserService().register(pal);
            UserServiceImpl.getUserService().register(robert);
            UserServiceImpl.getUserService().register(imre);
        } catch (UserAlreadyRegisteredException e) {
            e.printStackTrace();
        }

        //Initialize pages for demo
        Page pythonText = new TextPage("Python", "Python is an interpreted high-level programming language for general-purpose programming. Created by Guido van Rossum and first released in 1991, Python has a design philosophy that emphasizes code readability, notably using significant whitespace. It provides constructs that enable clear programming on both small and large scales.<br><br>\n" +
                "Python features a dynamic type system and automatic memory management. It supports multiple programming paradigms, including object-oriented, imperative, functional and procedural, and has a large and comprehensive standard library.<br><br>\n" +
                "Python interpreters are available for many operating systems. CPython, the reference implementation of Python, is open source software and has a community-based development model, as do nearly all of its variant implementations. CPython is managed by the non-profit Python Software Foundation.");
        Page pythonAssignment = new AssignmentPage("Hello Wold! in Python", "Create a script file that welcomes the user given to it. If no name was given, then it welcomes the whole world.<br><br>\n" +
                "Submit your repository containing the solution.<br><br>\n" +
                "Expectations<br><br>\n" +
                "- The name is provided as the command line argument of the script.<br>\n" +
                "- Create a flowchart to visualize your algorithm.<br>\n" +
                "- Structure your code: Separate some logic into functions.<br><br>\n" +
                "Upload only the python script file to your repository. Though you do not need to submit the flowchart the mentors may ask you to show us the flowchart during the grading.", 50);
        PageServiceImpl.getPageService().addNewPage(pythonText);
        PageServiceImpl.getPageService().addNewPage(pythonAssignment);

        Page javaText = new TextPage("Java", "Java is a general-purpose computer-programming language that is concurrent, class-based, object-oriented, and specifically designed to have as few implementation dependencies as possible. It is intended to let application developers \"write once, run anywhere\" (WORA), meaning that compiled Java code can run on all platforms that support Java without the need for recompilation. Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of computer architecture. As of 2016, Java is one of the most popular programming languages in use, particularly for client-server web applications, with a reported 9 million developers. Java was originally developed by James Gosling at Sun Microsystems (which has since been acquired by Oracle Corporation) and released in 1995 as a core component of Sun Microsystems' Java platform. The language derives much of its syntax from C and C++, but it has fewer low-level facilities than either of them.<br><br>\n" +
                "The original and reference implementation Java compilers, virtual machines, and class libraries were originally released by Sun under proprietary licenses. As of May 2007, in compliance with the specifications of the Java Community Process, Sun relicensed most of its Java technologies under the GNU General Public License. Others have also developed alternative implementations of these Sun technologies, such as the GNU Compiler for Java (bytecode compiler), GNU Classpath (standard libraries), and IcedTea-Web (browser plugin for applets).");
        Page javaAssignment = new AssignmentPage("Hello World! in Java", "The mentors at Codecool have been working so hard to teaching you the basics of programming with Python that they've completely forgotten how to write Java code, even a simple \"Hello World!\" program is difficult for them these days.<br><br>\n" +
                "The Task<br><br>\n" +
                "Your goal is to create your version of the well-known \"Hello World!\" application in Java:<br>\n" +
                "Create a script file that welcomes the user given to it. If no name was given, then it welcomes the whole world.<br><br>Submit your repository's URL containing the solution!", 80);
        PageServiceImpl.getPageService().addNewPage(javaText);
        PageServiceImpl.getPageService().addNewPage(javaAssignment);

        Page javaScriptText = new TextPage("JavaScript", "JavaScript, often abbreviated as JS, is a high-level, interpreted programming language. It is a language which is also characterized as dynamic, weakly typed, prototype-based and multi-paradigm. Alongside HTML and CSS, JavaScript is one of the three core technologies of World Wide Web content engineering. It is used to make dynamic webpages interactive and provide online programs, including video games. The majority of websites employ it, and all modern web browsers support it without the need for plug-ins by means of a built-in JavaScript engine. Each of the many JavaScript engines represent a different implementation of JavaScript, all based on the ECMAScript specification, with some engines not supporting the spec fully, and with many engines supporting additional features beyond ECMA.<br><br>\n" +
                "As a multi-paradigm language, JavaScript supports event-driven, functional, and imperative (including object-oriented and prototype-based) programming styles. It has an API for working with text, arrays, dates, regular expressions, and basic manipulation of the DOM, but the language itself does not include any I/O, such as networking, storage, or graphics facilities, relying for these upon the host environment in which it is embedded.<br><br>\n" +
                "Initially only implemented client-side in web browsers, JavaScript engines are now embedded in many other types of host software, including server-side in web servers and databases, and in non-web programs such as word processors and PDF software, and in runtime environments that make JavaScript available for writing mobile and desktop applications, including desktop widgets.");
        Page javaScriptAssignment = new AssignmentPage("Hello World! in JavaScript", "The mentors at Codecool have been working so hard to teaching you Java that they've completely forgotten how to write JavaScript code, even a simple \"Hello World!\" program is difficult for them these days.<br><br>\n" +
                "The Task<br><br>\n" +
                "Your goal is to create your version of the well-known \\\"Hello World!\\\" application in JavaScript:<br>\n" +
                "Create a script file that welcomes the user given to it. If no name was given, then it welcomes the whole world.<br><br>\n" +
                "Submit your repository's URL containing the solution!", 40);
        PageServiceImpl.getPageService().addNewPage(javaScriptText);
        PageServiceImpl.getPageService().addNewPage(javaScriptAssignment);
        */
        registerCharacterEncodingFilter(sce);
        DataSource dataSource = putDataSourceToServletContext(sce);
        runDatabaseInitScript(dataSource, "/init.sql");
    }

    private void registerCharacterEncodingFilter(ServletContextEvent sce) {
        sce.getServletContext().addFilter("SetCharacterEncodingFilter", "org.apache.catalina.filters.SetCharacterEncodingFilter");
    }

    private DataSource putDataSourceToServletContext(ServletContextEvent sce) {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envCtx.lookup("jdbc/ip");
            ServletContext servletCtx = sce.getServletContext();
            servletCtx.setAttribute("dataSource", dataSource);
            return dataSource;
        } catch (NamingException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }

    private void runDatabaseInitScript(DataSource dataSource, String resource) {
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource(resource));
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("This method is invoked once when the webapp gets undeployed.");
    }
}
