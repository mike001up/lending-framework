[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] pig                                                                [pom]
[INFO] pig-register                                                       [jar]
[INFO] pig-common                                                         [pom]
[INFO] pig-common-core                                                    [jar]
[INFO] pig-common-security                                                [jar]
[INFO] pig-common-feign                                                   [jar]
[INFO] pig-common-log                                                     [jar]
[INFO] pig-common-mybatis                                                 [jar]
[INFO] pig-common-excel                                                   [jar]
[INFO] pig-upms                                                           [pom]
[INFO] pig-upms-api                                                       [jar]
[INFO] pig-gateway                                                        [jar]
[INFO] pig-auth                                                           [jar]
[INFO] pig-common-oss                                                     [jar]
[INFO] pig-common-swagger                                                 [jar]
[INFO] pig-common-xss                                                     [jar]
[INFO] pig-upms-biz                                                       [jar]
[INFO] pig-common-bom                                                     [pom]
[INFO] pig-common-datasource                                              [jar]
[INFO] pig-common-seata                                                   [jar]
[INFO] pig-visual                                                         [pom]
[INFO] pig-codegen                                                        [jar]
[INFO] pig-monitor                                                        [jar]
[INFO] pig-quartz                                                         [jar]
[INFO] 
[INFO] -------------------------< com.pig4cloud:pig >--------------------------
[INFO] Building pig 3.8.3                                                [1/24]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig ---
[INFO] com.pig4cloud:pig:pom:3.8.3
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  |  \- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  |     +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  |     \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:runtime
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:runtime
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:runtime
[INFO] |  |  \- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:runtime
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] |     \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |        \- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] ---------------------< com.pig4cloud:pig-register >---------------------
[INFO] Building pig-register 3.8.3                                       [2/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-register ---
[INFO] com.pig4cloud:pig-register:jar:3.8.3
[INFO] +- io.github.pig-mesh.nacos:nacos-config:jar:2.5.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-web:jar:2.7.18:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-json:jar:2.7.18:compile
[INFO] |  |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.13.5:compile
[INFO] |  |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.13.5:compile
[INFO] |  |  +- org.springframework:spring-web:jar:5.3.31:compile
[INFO] |  |  \- org.springframework:spring-webmvc:jar:5.3.31:compile
[INFO] |  +- io.github.pig-mesh.nacos:nacos-api:jar:2.5.0:compile
[INFO] |  |  +- io.grpc:grpc-util:jar:1.64.2:compile
[INFO] |  |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.23:runtime
[INFO] |  |  \- javax.annotation:javax.annotation-api:jar:1.3.2:compile
[INFO] |  +- io.github.pig-mesh.nacos:nacos-core:jar:2.5.0:compile
[INFO] |  |  +- io.github.pig-mesh.nacos:nacos-common:jar:2.5.0:compile
[INFO] |  |  +- io.github.pig-mesh.nacos:nacos-consistency:jar:2.5.0:compile
[INFO] |  |  |  \- com.caucho:hessian:jar:4.0.63:compile
[INFO] |  |  +- io.github.pig-mesh.nacos:nacos-auth:jar:2.5.0:compile
[INFO] |  |  +- io.github.pig-mesh.nacos:nacos-trace-plugin:jar:2.5.0:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.98:compile
[INFO] |  |  |  \- org.apache.tomcat:tomcat-annotations-api:jar:9.0.98:compile
[INFO] |  |  +- com.alipay.sofa:jraft-core:jar:1.3.14:compile
[INFO] |  |  |  +- org.ow2.asm:asm:jar:6.0:compile
[INFO] |  |  |  +- org.rocksdb:rocksdbjni:jar:8.8.1:compile
[INFO] |  |  |  +- net.java.dev.jna:jna:jar:5.5.0:compile
[INFO] |  |  |  +- org.jctools:jctools-core:jar:2.1.1:compile
[INFO] |  |  |  +- com.lmax:disruptor:jar:3.3.7:compile
[INFO] |  |  |  +- commons-lang:commons-lang:jar:2.6:compile
[INFO] |  |  |  +- com.alipay.sofa:hessian:jar:3.3.6:compile
[INFO] |  |  |  \- io.dropwizard.metrics:metrics-core:jar:4.2.22:compile
[INFO] |  |  \- com.alipay.sofa:rpc-grpc-impl:jar:1.3.14:compile
[INFO] |  +- io.github.pig-mesh.nacos:nacos-persistence:jar:2.5.0:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.18:compile
[INFO] |  |  |  +- com.zaxxer:HikariCP:jar:4.0.3:compile
[INFO] |  |  |  \- org.springframework:spring-jdbc:jar:5.3.31:compile
[INFO] |  |  +- io.github.pig-mesh.nacos:nacos-sys:jar:2.5.0:compile
[INFO] |  |  |  \- io.github.pig-mesh.nacos:nacos-custom-environment-plugin:jar:2.5.0:compile
[INFO] |  |  \- org.apache.derby:derby:jar:10.14.2.0:compile
[INFO] |  +- commons-io:commons-io:jar:2.18.0:compile
[INFO] |  +- ch.qos.logback:logback-classic:jar:1.2.12:compile
[INFO] |  +- io.github.pig-mesh.nacos:nacos-encryption-plugin:jar:2.5.0:compile
[INFO] |  +- io.github.pig-mesh.nacos:nacos-config-plugin:jar:2.5.0:compile
[INFO] |  +- org.apache.httpcomponents:httpasyncclient:jar:4.1.5:compile
[INFO] |  |  \- org.apache.httpcomponents:httpcore-nio:jar:4.4.16:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-tomcat:jar:2.7.18:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:1.3.5:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:9.0.98:compile
[INFO] |  |  \- org.apache.tomcat.embed:tomcat-embed-websocket:jar:9.0.98:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-core:jar:2.13.5:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.13.5:compile
[INFO] |  |  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.13.5:compile
[INFO] |  +- io.micrometer:micrometer-registry-prometheus:jar:1.9.17:compile
[INFO] |  |  \- io.prometheus:simpleclient_common:jar:0.15.0:compile
[INFO] |  +- io.micrometer:micrometer-registry-influx:jar:1.9.17:compile
[INFO] |  +- io.micrometer:micrometer-registry-elastic:jar:1.9.17:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-aop:jar:2.7.18:compile
[INFO] |  |  \- org.aspectj:aspectjweaver:jar:1.9.7:compile
[INFO] |  +- org.yaml:snakeyaml:jar:1.30:compile
[INFO] |  +- io.github.pig-mesh.nacos:nacos-control-plugin:jar:2.5.0:compile
[INFO] |  \- io.github.pig-mesh.nacos:nacos-datasource-plugin:jar:2.5.0:compile
[INFO] +- io.github.pig-mesh.nacos:nacos-naming:jar:2.5.0:compile
[INFO] |  +- org.springframework.boot:spring-boot:jar:2.7.18:compile
[INFO] |  |  \- org.springframework:spring-context:jar:5.3.31:compile
[INFO] |  +- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  +- org.slf4j:slf4j-api:jar:1.7.36:compile
[INFO] |  +- ch.qos.logback:logback-core:jar:1.2.12:compile
[INFO] |  +- org.apache.httpcomponents:httpcore:jar:4.4.16:compile
[INFO] |  +- org.apache.httpcomponents:httpclient:jar:4.5.14:compile
[INFO] |  |  \- commons-codec:commons-codec:jar:1.15:compile
[INFO] |  +- com.mysql:mysql-connector-j:jar:8.0.33:compile
[INFO] |  +- org.slf4j:log4j-over-slf4j:jar:1.7.36:compile
[INFO] |  +- org.slf4j:jcl-over-slf4j:jar:1.7.36:compile
[INFO] |  +- org.slf4j:jul-to-slf4j:jar:1.7.36:compile
[INFO] |  \- io.github.pig-mesh.nacos:nacos-cmdb:jar:2.5.0:compile
[INFO] +- io.github.pig-mesh.nacos:nacos-istio:jar:2.5.0:compile
[INFO] |  +- io.github.pig-mesh.nacos:nacos-client:jar:2.5.0:compile
[INFO] |  |  +- io.github.pig-mesh.nacos:nacos-auth-plugin:jar:2.5.0:compile
[INFO] |  |  +- io.github.pig-mesh.nacos:nacos-logback-adapter-12:jar:2.5.0:compile
[INFO] |  |  +- com.alibaba.nacos:logback-adapter:jar:1.1.3:compile
[INFO] |  |  +- io.github.pig-mesh.nacos:nacos-log4j2-adapter:jar:2.5.0:compile
[INFO] |  |  \- io.prometheus:simpleclient:jar:0.15.0:compile
[INFO] |  |     +- io.prometheus:simpleclient_tracer_otel:jar:0.15.0:compile
[INFO] |  |     |  \- io.prometheus:simpleclient_tracer_common:jar:0.15.0:compile
[INFO] |  |     \- io.prometheus:simpleclient_tracer_otel_agent:jar:0.15.0:compile
[INFO] |  +- io.grpc:grpc-netty-shaded:jar:1.64.2:compile
[INFO] |  |  +- io.grpc:grpc-core:jar:1.64.2:compile
[INFO] |  |  |  +- com.google.code.gson:gson:jar:2.9.1:runtime
[INFO] |  |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.64.2:runtime
[INFO] |  |  +- com.google.guava:guava:jar:32.1.3-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- org.checkerframework:checker-qual:jar:3.37.0:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:2.8:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.23.0:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.26.0:runtime
[INFO] |  |  \- io.grpc:grpc-api:jar:1.64.2:compile
[INFO] |  +- io.grpc:grpc-protobuf:jar:1.64.2:compile
[INFO] |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  \- io.grpc:grpc-protobuf-lite:jar:1.64.2:runtime
[INFO] |  +- io.grpc:grpc-stub:jar:1.64.2:compile
[INFO] |  +- com.google.api.grpc:proto-google-common-protos:jar:2.17.0:compile
[INFO] |  +- com.google.protobuf:protobuf-java:jar:3.25.5:compile
[INFO] |  \- io.envoyproxy.controlplane:api:jar:0.1.27:compile
[INFO] +- io.github.pig-mesh.nacos:nacos-default-plugin-all:jar:2.5.0:compile
[INFO] |  +- io.github.pig-mesh.nacos:default-auth-plugin:jar:2.5.0:compile
[INFO] |  |  \- org.springframework.ldap:spring-ldap-core:jar:2.4.1:compile
[INFO] |  |     \- org.springframework:spring-tx:jar:5.3.31:compile
[INFO] |  \- io.github.pig-mesh.nacos:default-control-plugin:jar:2.5.0:compile
[INFO] +- io.github.pig-mesh.nacos:nacos-prometheus:jar:2.5.0:compile
[INFO] +- org.springframework.boot:spring-boot-starter-security:jar:2.7.18:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:2.7.18:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:2.7.18:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-starter-logging:jar:2.7.18:compile
[INFO] |  |     \- org.apache.logging.log4j:log4j-to-slf4j:jar:2.17.2:compile
[INFO] |  |        \- org.apache.logging.log4j:log4j-api:jar:2.17.2:compile
[INFO] |  +- org.springframework:spring-aop:jar:5.3.31:compile
[INFO] |  |  \- org.springframework:spring-beans:jar:5.3.31:compile
[INFO] |  +- org.springframework.security:spring-security-config:jar:5.7.11:compile
[INFO] |  |  \- org.springframework.security:spring-security-core:jar:5.7.11:compile
[INFO] |  |     \- org.springframework.security:spring-security-crypto:jar:5.7.11:compile
[INFO] |  \- org.springframework.security:spring-security-web:jar:5.7.11:compile
[INFO] |     \- org.springframework:spring-expression:jar:5.3.31:compile
[INFO] +- cn.hutool:hutool-system:jar:5.8.36:compile
[INFO] |  \- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:2.7.16:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:2.7.16:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:2.7.18:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:2.7.18:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:2.7.18:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-actuator:jar:2.7.18:compile
[INFO] |  |  \- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.13.5:compile
[INFO] |  \- io.micrometer:micrometer-core:jar:1.9.17:compile
[INFO] |     +- org.hdrhistogram:HdrHistogram:jar:2.1.12:compile
[INFO] |     \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- org.projectlombok:lombok:jar:1.18.30:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:2.7.18:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:2.7.18:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:2.7.18:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.7.0:test
[INFO]    |  \- net.minidev:json-smart:jar:2.4.11:test
[INFO]    |     \- net.minidev:accessors-smart:jar:2.4.11:test
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:2.3.3:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:1.2.2:compile
[INFO]    +- org.assertj:assertj-core:jar:3.22.0:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.8.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.8.2:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.2.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.8.2:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.8.2:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.8.2:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.8.2:test
[INFO]    +- org.mockito:mockito-core:jar:4.5.1:test
[INFO]    |  +- net.bytebuddy:byte-buddy:jar:1.12.23:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.12.23:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.2:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:4.5.1:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.1:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:5.3.31:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:5.3.31:compile
[INFO]    +- org.springframework:spring-test:jar:5.3.31:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.9.1:test
[INFO] 
[INFO] ----------------------< com.pig4cloud:pig-common >----------------------
[INFO] Building pig-common 3.8.3                                         [3/24]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common ---
[INFO] com.pig4cloud:pig-common:pom:3.8.3
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  |  \- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  |     +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  |     \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:runtime
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:runtime
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:runtime
[INFO] |  |  \- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:runtime
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] |     \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |        \- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] -------------------< com.pig4cloud:pig-common-core >--------------------
[INFO] Building pig-common-core 3.8.3                                    [4/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-core ---
[INFO] com.pig4cloud:pig-common-core:jar:3.8.3
[INFO] +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |     \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |     +- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |     \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  +- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] +- org.springframework:spring-webmvc:jar:6.2.5:provided
[INFO] |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-core:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO] |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |        |  +- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |        |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |        +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] +- org.apache.rocketmq:rocketmq-client:jar:5.3.0:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.0:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.0:compile
[INFO] |  |  |  +- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:runtime
[INFO] |  |  |  |  |     +- com.squareup.okio:okio:jar:3.6.0:runtime
[INFO] |  |  |  |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:runtime
[INFO] |  |  |  |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:runtime
[INFO] |  |  |  |  |        |  \- org.jetbrains:annotations:jar:13.0:runtime
[INFO] |  |  |  |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:runtime
[INFO] |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  +- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  \- org.awaitility:awaitility:jar:4.2.2:compile
[INFO] +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  +- com.google.errorprone:error_prone_annotations:jar:2.14.0:compile
[INFO] |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] +- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] -----------------< com.pig4cloud:pig-common-security >------------------
[INFO] Building pig-common-security 3.8.3                                [5/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-security ---
[INFO] com.pig4cloud:pig-common-security:jar:3.8.3
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |     \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |     \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  +- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |        +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:runtime
[INFO] |  |  |  |  |  |     +- com.squareup.okio:okio:jar:3.6.0:runtime
[INFO] |  |  |  |  |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        |  \- org.jetbrains:annotations:jar:13.0:runtime
[INFO] |  |  |  |  |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.14.0:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- cn.hutool:hutool-extra:jar:5.8.36:compile
[INFO] |  \- cn.hutool:hutool-setting:jar:5.8.36:compile
[INFO] |     \- cn.hutool:hutool-log:jar:5.8.36:compile
[INFO] +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] +- org.springframework.cloud:spring-cloud-starter-openfeign:jar:4.2.1:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] |  |  \- org.bouncycastle:bcprov-jdk18on:jar:1.78.1:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-openfeign-core:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- io.github.openfeign:feign-form-spring:jar:13.5:compile
[INFO] |  |     +- io.github.openfeign:feign-form:jar:13.5:compile
[INFO] |  |     \- commons-fileupload:commons-fileupload:jar:1.5:compile
[INFO] |  +- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |  +- io.github.openfeign:feign-core:jar:13.5:compile
[INFO] |  \- io.github.openfeign:feign-slf4j:jar:13.5:compile
[INFO] |     \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] +- org.springframework.security:spring-security-oauth2-jose:jar:6.4.4:compile
[INFO] |  +- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-core:jar:6.4.4:compile
[INFO] |  +- org.springframework:spring-core:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO] |  \- com.nimbusds:nimbus-jose-jwt:jar:9.37.3:compile
[INFO] |     \- com.github.stephenc.jcip:jcip-annotations:jar:1.0-1:compile
[INFO] +- org.springframework.security:spring-security-oauth2-authorization-server:jar:1.4.2:compile
[INFO] |  +- org.springframework.security:spring-security-config:jar:6.4.4:compile
[INFO] |  +- org.springframework.security:spring-security-web:jar:6.4.4:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-resource-server:jar:6.4.4:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |     +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |     \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] +- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-starter-aop:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] -------------------< com.pig4cloud:pig-common-feign >-------------------
[INFO] Building pig-common-feign 3.8.3                                   [6/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-feign ---
[INFO] com.pig4cloud:pig-common-feign:jar:3.8.3
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  |     \- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  \- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.csp:sentinel-transport-simple-http:jar:1.8.8:compile
[INFO] |  |  \- com.alibaba.csp:sentinel-transport-common:jar:1.8.8:compile
[INFO] |  |     +- com.alibaba.csp:sentinel-datasource-extension:jar:1.8.8:compile
[INFO] |  |     \- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  +- com.alibaba.csp:sentinel-annotation-aspectj:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-core:jar:1.8.8:compile
[INFO] |  |  \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-circuitbreaker-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  \- com.alibaba.csp:sentinel-reactor-adapter:jar:1.8.8:compile
[INFO] |  +- com.alibaba.csp:sentinel-spring-webflux-adapter:jar:1.8.8:compile
[INFO] |  +- com.alibaba.csp:sentinel-spring-webmvc-v6x-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-web-adapter-common:jar:1.8.8:compile
[INFO] |  |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] |  +- com.alibaba.csp:sentinel-parameter-flow-control:jar:1.8.8:compile
[INFO] |  |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  +- com.alibaba.csp:sentinel-cluster-server-default:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-common-default:jar:1.8.8:compile
[INFO] |  |  \- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |     \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  +- com.alibaba.csp:sentinel-cluster-client-default:jar:1.8.8:compile
[INFO] |  \- com.alibaba.cloud:spring-cloud-alibaba-sentinel-datasource:jar:2023.0.3.2:compile
[INFO] |     \- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2023.0.3.2:compile
[INFO] +- org.springframework.cloud:spring-cloud-starter-openfeign:jar:4.2.1:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] |  |  \- org.bouncycastle:bcprov-jdk18on:jar:1.78.1:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-openfeign-core:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- io.github.openfeign:feign-form-spring:jar:13.5:compile
[INFO] |  |     +- io.github.openfeign:feign-form:jar:13.5:compile
[INFO] |  |     \- commons-fileupload:commons-fileupload:jar:1.5:compile
[INFO] |  +- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |  +- io.github.openfeign:feign-core:jar:13.5:compile
[INFO] |  \- io.github.openfeign:feign-slf4j:jar:13.5:compile
[INFO] +- io.github.openfeign:feign-okhttp:jar:13.5:compile
[INFO] |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:compile
[INFO] |     +- com.squareup.okio:okio:jar:3.6.0:compile
[INFO] |     |  \- com.squareup.okio:okio-jvm:jar:3.6.0:compile
[INFO] |     |     \- org.jetbrains.kotlin:kotlin-stdlib-common:jar:1.9.25:compile
[INFO] |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:compile
[INFO] |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:compile
[INFO] |        |  \- org.jetbrains:annotations:jar:13.0:compile
[INFO] |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:compile
[INFO] +- org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:4.2.1:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-loadbalancer:jar:4.2.1:compile
[INFO] |  |  +- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |  \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  \- io.projectreactor.addons:reactor-extra:jar:3.5.2:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-cache:jar:3.4.4:compile
[INFO] |  |  \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  \- com.stoyanr:evictor:jar:1.0.0:compile
[INFO] +- com.github.ben-manes.caffeine:caffeine:jar:3.1.8:compile
[INFO] |  +- org.checkerframework:checker-qual:jar:3.37.0:compile
[INFO] |  \- com.google.errorprone:error_prone_annotations:jar:2.21.1:compile
[INFO] +- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |  +- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-core:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  \- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] +- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] --------------------< com.pig4cloud:pig-common-log >--------------------
[INFO] Building pig-common-log 3.8.3                                     [7/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-log ---
[INFO] com.pig4cloud:pig-common-log:jar:3.8.3
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |     \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |     \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  \- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.14.0:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- cn.hutool:hutool-extra:jar:5.8.36:compile
[INFO] |  \- cn.hutool:hutool-setting:jar:5.8.36:compile
[INFO] |     \- cn.hutool:hutool-log:jar:5.8.36:compile
[INFO] +- cn.hutool:hutool-http:jar:5.8.36:compile
[INFO] +- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |  +- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-core:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  \- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] +- org.springframework.security:spring-security-oauth2-core:jar:6.4.4:compile
[INFO] |  \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] +- com.pig4cloud:pig-common-feign:jar:3.8.3:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-transport-simple-http:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-transport-common:jar:1.8.8:compile
[INFO] |  |  |     \- com.alibaba.csp:sentinel-datasource-extension:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-annotation-aspectj:jar:1.8.8:compile
[INFO] |  |  |  +- com.alibaba.csp:sentinel-core:jar:1.8.8:compile
[INFO] |  |  |  \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] |  |  +- com.alibaba.cloud:spring-cloud-circuitbreaker-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-reactor-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webflux-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webmvc-v6x-adapter:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-web-adapter-common:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-parameter-flow-control:jar:1.8.8:compile
[INFO] |  |  |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-server-default:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-cluster-common-default:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-client-default:jar:1.8.8:compile
[INFO] |  |  \- com.alibaba.cloud:spring-cloud-alibaba-sentinel-datasource:jar:2023.0.3.2:compile
[INFO] |  |     \- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2023.0.3.2:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-openfeign:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-starter:jar:4.2.1:compile
[INFO] |  |  |  +- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] |  |  |  \- org.bouncycastle:bcprov-jdk18on:jar:1.78.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-openfeign-core:jar:4.2.1:compile
[INFO] |  |  |  \- io.github.openfeign:feign-form-spring:jar:13.5:compile
[INFO] |  |  |     +- io.github.openfeign:feign-form:jar:13.5:compile
[INFO] |  |  |     \- commons-fileupload:commons-fileupload:jar:1.5:compile
[INFO] |  |  +- io.github.openfeign:feign-core:jar:13.5:compile
[INFO] |  |  \- io.github.openfeign:feign-slf4j:jar:13.5:compile
[INFO] |  +- io.github.openfeign:feign-okhttp:jar:13.5:compile
[INFO] |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:compile
[INFO] |  |     +- com.squareup.okio:okio:jar:3.6.0:compile
[INFO] |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:compile
[INFO] |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:compile
[INFO] |  |        |  \- org.jetbrains:annotations:jar:13.0:compile
[INFO] |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-loadbalancer:jar:4.2.1:compile
[INFO] |  |  |  \- io.projectreactor.addons:reactor-extra:jar:3.5.2:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-cache:jar:3.4.4:compile
[INFO] |  |  \- com.stoyanr:evictor:jar:1.0.0:compile
[INFO] |  +- com.github.ben-manes.caffeine:caffeine:jar:3.1.8:compile
[INFO] |  |  \- org.checkerframework:checker-qual:jar:3.37.0:compile
[INFO] |  \- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] ------------------< com.pig4cloud:pig-common-mybatis >------------------
[INFO] Building pig-common-mybatis 3.8.3                                 [8/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-mybatis ---
[INFO] com.pig4cloud:pig-common-mybatis:jar:3.8.3
[INFO] +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] +- com.baomidou:mybatis-plus-spring:jar:3.5.11:compile
[INFO] |  \- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] +- com.baomidou:mybatis-plus-jsqlparser:jar:3.5.11:compile
[INFO] |  +- com.github.jsqlparser:jsqlparser:jar:5.1:compile
[INFO] |  \- com.baomidou:mybatis-plus-jsqlparser-common:jar:3.5.11:compile
[INFO] +- io.swagger.core.v3:swagger-annotations-jakarta:jar:2.2.29:compile
[INFO] +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:provided
[INFO] +- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-core:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] +- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |  +- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  \- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |     \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |     \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  +- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |        +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:runtime
[INFO] |  |  |  |  |  |     +- com.squareup.okio:okio:jar:3.6.0:runtime
[INFO] |  |  |  |  |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        |  \- org.jetbrains:annotations:jar:13.0:runtime
[INFO] |  |  |  |  |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.14.0:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] -------------------< com.pig4cloud:pig-common-excel >-------------------
[INFO] Building pig-common-excel 3.8.3                                   [9/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-excel ---
[INFO] com.pig4cloud:pig-common-excel:jar:3.8.3
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |     \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |     \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  |  |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  +- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:runtime
[INFO] |  |  |  |  |  |     +- com.squareup.okio:okio:jar:3.6.0:runtime
[INFO] |  |  |  |  |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        |  \- org.jetbrains:annotations:jar:13.0:runtime
[INFO] |  |  |  |  |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.14.0:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- com.pig4cloud.excel:excel-spring-boot-starter:jar:3.4.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-aop:jar:3.4.4:compile
[INFO] |  |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] |  +- cn.idev.excel:fastexcel:jar:1.0.0:compile
[INFO] |  |  \- cn.idev.excel:fastexcel-core:jar:1.0.0:compile
[INFO] |  |     +- org.apache.commons:commons-csv:jar:1.11.0:compile
[INFO] |  |     +- org.apache.poi:poi:jar:5.2.5:compile
[INFO] |  |     |  +- org.apache.commons:commons-collections4:jar:4.4:compile
[INFO] |  |     |  +- org.apache.commons:commons-math3:jar:3.6.1:compile
[INFO] |  |     |  +- com.zaxxer:SparseBitSet:jar:1.3:compile
[INFO] |  |     |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |     +- org.apache.poi:poi-ooxml:jar:5.2.5:compile
[INFO] |  |     |  +- org.apache.poi:poi-ooxml-lite:jar:5.2.5:compile
[INFO] |  |     |  +- org.apache.xmlbeans:xmlbeans:jar:5.2.0:compile
[INFO] |  |     |  \- com.github.virtuald:curvesapi:jar:1.08:compile
[INFO] |  |     +- org.ehcache:ehcache:jar:3.10.8:compile
[INFO] |  |     |  +- javax.cache:cache-api:jar:1.1.1:compile
[INFO] |  |     |  \- org.glassfish.jaxb:jaxb-runtime:jar:4.0.5:runtime
[INFO] |  |     |     \- org.glassfish.jaxb:jaxb-core:jar:4.0.5:runtime
[INFO] |  |     |        +- org.glassfish.jaxb:txw2:jar:4.0.5:runtime
[INFO] |  |     |        \- com.sun.istack:istack-commons-runtime:jar:4.1.2:runtime
[INFO] |  |     +- cn.idev.excel:fastexcel-support:jar:1.0.0:compile
[INFO] |  |     +- com.itextpdf:itext7-core:pom:7.1.15:compile
[INFO] |  |     |  +- com.itextpdf:barcodes:jar:7.1.15:compile
[INFO] |  |     |  +- com.itextpdf:hyph:jar:7.1.15:compile
[INFO] |  |     |  +- com.itextpdf:sign:jar:7.1.15:compile
[INFO] |  |     |  +- com.itextpdf:styled-xml-parser:jar:7.1.15:compile
[INFO] |  |     |  \- com.itextpdf:svg:jar:7.1.15:compile
[INFO] |  |     +- com.itextpdf:kernel:jar:7.1.15:compile
[INFO] |  |     |  +- org.bouncycastle:bcpkix-jdk15on:jar:1.68:compile
[INFO] |  |     |  \- org.bouncycastle:bcprov-jdk15on:jar:1.68:compile
[INFO] |  |     +- com.itextpdf:io:jar:7.1.15:compile
[INFO] |  |     +- com.itextpdf:layout:jar:7.1.15:compile
[INFO] |  |     +- com.itextpdf:forms:jar:7.1.15:compile
[INFO] |  |     +- com.itextpdf:pdfa:jar:7.1.15:compile
[INFO] |  |     +- com.itextpdf:pdftest:jar:7.1.15:compile
[INFO] |  |     |  +- junit:junit:jar:4.13.2:compile
[INFO] |  |     |  |  \- org.hamcrest:hamcrest-core:jar:2.2:compile
[INFO] |  |     |  \- org.verapdf:validation-model:jar:1.16.1:compile
[INFO] |  |     |     +- org.verapdf:parser:jar:1.16.1:compile
[INFO] |  |     |     +- org.verapdf:pdf-model:jar:1.16.1:compile
[INFO] |  |     |     +- org.verapdf:core:jar:1.16.1:compile
[INFO] |  |     |     |  +- org.verapdf:verapdf-xmp-core:jar:1.16.1:compile
[INFO] |  |     |     |  +- org.mozilla:rhino:jar:1.7.11:compile
[INFO] |  |     |     |  +- javax.xml.bind:jaxb-api:jar:2.4.0-b180830.0359:compile
[INFO] |  |     |     |  |  \- javax.activation:javax.activation-api:jar:1.2.0:compile
[INFO] |  |     |     |  \- net.java.dev.stax-utils:stax-utils:jar:20070216:compile
[INFO] |  |     |     +- org.verapdf:feature-reporting:jar:1.16.1:compile
[INFO] |  |     |     \- org.verapdf:metadata-fixer:jar:1.16.1:compile
[INFO] |  |     \- com.itextpdf:font-asian:jar:7.1.15:compile
[INFO] |  \- org.apache.commons:commons-compress:jar:1.27.1:compile
[INFO] |     +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |     \- commons-io:commons-io:jar:2.18.0:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] -----------------------< com.pig4cloud:pig-upms >-----------------------
[INFO] Building pig-upms 3.8.3                                          [10/24]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-upms ---
[INFO] com.pig4cloud:pig-upms:pom:3.8.3
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  |  \- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  |     +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  |     \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:runtime
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:runtime
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:runtime
[INFO] |  |  \- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:runtime
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] |     \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |        \- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] ---------------------< com.pig4cloud:pig-upms-api >---------------------
[INFO] Building pig-upms-api 3.8.3                                      [11/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-upms-api ---
[INFO] com.pig4cloud:pig-upms-api:jar:3.8.3
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |     \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |     \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  \- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.14.0:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- com.pig4cloud:pig-common-feign:jar:3.8.3:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-transport-simple-http:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-transport-common:jar:1.8.8:compile
[INFO] |  |  |     \- com.alibaba.csp:sentinel-datasource-extension:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-annotation-aspectj:jar:1.8.8:compile
[INFO] |  |  |  +- com.alibaba.csp:sentinel-core:jar:1.8.8:compile
[INFO] |  |  |  \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] |  |  +- com.alibaba.cloud:spring-cloud-circuitbreaker-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-reactor-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webflux-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webmvc-v6x-adapter:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-web-adapter-common:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-parameter-flow-control:jar:1.8.8:compile
[INFO] |  |  |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-server-default:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-cluster-common-default:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-client-default:jar:1.8.8:compile
[INFO] |  |  \- com.alibaba.cloud:spring-cloud-alibaba-sentinel-datasource:jar:2023.0.3.2:compile
[INFO] |  |     \- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2023.0.3.2:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-openfeign:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-starter:jar:4.2.1:compile
[INFO] |  |  |  +- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] |  |  |  \- org.bouncycastle:bcprov-jdk18on:jar:1.78.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-openfeign-core:jar:4.2.1:compile
[INFO] |  |  |  \- io.github.openfeign:feign-form-spring:jar:13.5:compile
[INFO] |  |  |     +- io.github.openfeign:feign-form:jar:13.5:compile
[INFO] |  |  |     \- commons-fileupload:commons-fileupload:jar:1.5:compile
[INFO] |  |  +- io.github.openfeign:feign-core:jar:13.5:compile
[INFO] |  |  \- io.github.openfeign:feign-slf4j:jar:13.5:compile
[INFO] |  +- io.github.openfeign:feign-okhttp:jar:13.5:compile
[INFO] |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:compile
[INFO] |  |     +- com.squareup.okio:okio:jar:3.6.0:compile
[INFO] |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:compile
[INFO] |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:compile
[INFO] |  |        |  \- org.jetbrains:annotations:jar:13.0:compile
[INFO] |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-loadbalancer:jar:4.2.1:compile
[INFO] |  |  |  \- io.projectreactor.addons:reactor-extra:jar:3.5.2:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-cache:jar:3.4.4:compile
[INFO] |  |  \- com.stoyanr:evictor:jar:1.0.0:compile
[INFO] |  +- com.github.ben-manes.caffeine:caffeine:jar:3.1.8:compile
[INFO] |  |  \- org.checkerframework:checker-qual:jar:3.37.0:compile
[INFO] |  +- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |  |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  \- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] +- com.pig4cloud:pig-common-mybatis:jar:3.8.3:compile
[INFO] |  +- com.baomidou:mybatis-plus-spring:jar:3.5.11:compile
[INFO] |  +- com.baomidou:mybatis-plus-jsqlparser:jar:3.5.11:compile
[INFO] |  |  +- com.github.jsqlparser:jsqlparser:jar:5.1:compile
[INFO] |  |  \- com.baomidou:mybatis-plus-jsqlparser-common:jar:3.5.11:compile
[INFO] |  \- io.swagger.core.v3:swagger-annotations-jakarta:jar:2.2.29:compile
[INFO] +- com.pig4cloud:pig-common-excel:jar:3.8.3:compile
[INFO] |  \- com.pig4cloud.excel:excel-spring-boot-starter:jar:3.4.0:compile
[INFO] |     +- org.springframework.boot:spring-boot-starter-aop:jar:3.4.4:compile
[INFO] |     +- cn.idev.excel:fastexcel:jar:1.0.0:compile
[INFO] |     |  \- cn.idev.excel:fastexcel-core:jar:1.0.0:compile
[INFO] |     |     +- org.apache.commons:commons-csv:jar:1.11.0:compile
[INFO] |     |     +- org.apache.poi:poi:jar:5.2.5:compile
[INFO] |     |     |  +- org.apache.commons:commons-collections4:jar:4.4:compile
[INFO] |     |     |  +- org.apache.commons:commons-math3:jar:3.6.1:compile
[INFO] |     |     |  \- com.zaxxer:SparseBitSet:jar:1.3:compile
[INFO] |     |     +- org.apache.poi:poi-ooxml:jar:5.2.5:compile
[INFO] |     |     |  +- org.apache.poi:poi-ooxml-lite:jar:5.2.5:compile
[INFO] |     |     |  +- org.apache.xmlbeans:xmlbeans:jar:5.2.0:compile
[INFO] |     |     |  \- com.github.virtuald:curvesapi:jar:1.08:compile
[INFO] |     |     +- org.ehcache:ehcache:jar:3.10.8:compile
[INFO] |     |     |  +- javax.cache:cache-api:jar:1.1.1:compile
[INFO] |     |     |  \- org.glassfish.jaxb:jaxb-runtime:jar:4.0.5:runtime
[INFO] |     |     |     \- org.glassfish.jaxb:jaxb-core:jar:4.0.5:runtime
[INFO] |     |     |        +- org.glassfish.jaxb:txw2:jar:4.0.5:runtime
[INFO] |     |     |        \- com.sun.istack:istack-commons-runtime:jar:4.1.2:runtime
[INFO] |     |     +- cn.idev.excel:fastexcel-support:jar:1.0.0:compile
[INFO] |     |     +- com.itextpdf:itext7-core:pom:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:barcodes:jar:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:hyph:jar:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:sign:jar:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:styled-xml-parser:jar:7.1.15:compile
[INFO] |     |     |  \- com.itextpdf:svg:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:kernel:jar:7.1.15:compile
[INFO] |     |     |  +- org.bouncycastle:bcpkix-jdk15on:jar:1.68:compile
[INFO] |     |     |  \- org.bouncycastle:bcprov-jdk15on:jar:1.68:compile
[INFO] |     |     +- com.itextpdf:io:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:layout:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:forms:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:pdfa:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:pdftest:jar:7.1.15:compile
[INFO] |     |     |  +- junit:junit:jar:4.13.2:compile
[INFO] |     |     |  |  \- org.hamcrest:hamcrest-core:jar:2.2:compile
[INFO] |     |     |  \- org.verapdf:validation-model:jar:1.16.1:compile
[INFO] |     |     |     +- org.verapdf:parser:jar:1.16.1:compile
[INFO] |     |     |     +- org.verapdf:pdf-model:jar:1.16.1:compile
[INFO] |     |     |     +- org.verapdf:core:jar:1.16.1:compile
[INFO] |     |     |     |  +- org.verapdf:verapdf-xmp-core:jar:1.16.1:compile
[INFO] |     |     |     |  +- org.mozilla:rhino:jar:1.7.11:compile
[INFO] |     |     |     |  +- javax.xml.bind:jaxb-api:jar:2.4.0-b180830.0359:compile
[INFO] |     |     |     |  |  \- javax.activation:javax.activation-api:jar:1.2.0:compile
[INFO] |     |     |     |  \- net.java.dev.stax-utils:stax-utils:jar:20070216:compile
[INFO] |     |     |     +- org.verapdf:feature-reporting:jar:1.16.1:compile
[INFO] |     |     |     \- org.verapdf:metadata-fixer:jar:1.16.1:compile
[INFO] |     |     \- com.itextpdf:font-asian:jar:7.1.15:compile
[INFO] |     \- org.apache.commons:commons-compress:jar:1.27.1:compile
[INFO] |        +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |        \- commons-io:commons-io:jar:2.18.0:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] ---------------------< com.pig4cloud:pig-gateway >----------------------
[INFO] Building pig-gateway 3.8.3                                       [12/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-gateway ---
[INFO] com.pig4cloud:pig-gateway:jar:3.8.3
[INFO] +- org.springframework.cloud:spring-cloud-starter-gateway:jar:4.2.1:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter:jar:4.2.1:compile
[INFO] |  |  \- org.bouncycastle:bcprov-jdk18on:jar:1.78.1:compile
[INFO] |  \- org.springframework.cloud:spring-cloud-gateway-server:jar:4.2.1:compile
[INFO] |     \- io.projectreactor.addons:reactor-extra:jar:3.5.2:compile
[INFO] +- org.springframework.boot:spring-boot-starter-data-redis-reactive:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  \- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  +- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |     +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |     \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.nacos:nacos-client:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-auth-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-encryption-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-logback-adapter-12:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:logback-adapter:jar:1.1.3:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-log4j2-adapter:jar:2.4.2:compile
[INFO] |  |  +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  +- org.apache.httpcomponents:httpasyncclient:jar:4.1.5:compile
[INFO] |  |  |  +- org.apache.httpcomponents:httpcore-nio:jar:4.4.16:compile
[INFO] |  |  |  \- org.apache.httpcomponents:httpclient:jar:4.5.13:compile
[INFO] |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.16:compile
[INFO] |  |  +- io.prometheus:simpleclient:jar:0.16.0:compile
[INFO] |  |  |  +- io.prometheus:simpleclient_tracer_otel:jar:0.16.0:compile
[INFO] |  |  |  |  \- io.prometheus:simpleclient_tracer_common:jar:0.16.0:compile
[INFO] |  |  |  \- io.prometheus:simpleclient_tracer_otel_agent:jar:0.16.0:compile
[INFO] |  |  \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |  |     +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |  |     \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  \- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] |  \- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] +- org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:4.2.1:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-loadbalancer:jar:4.2.1:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-cache:jar:3.4.4:compile
[INFO] |  \- com.stoyanr:evictor:jar:1.0.0:compile
[INFO] +- com.github.ben-manes.caffeine:caffeine:jar:3.1.8:compile
[INFO] |  +- org.checkerframework:checker-qual:jar:3.37.0:compile
[INFO] |  \- com.google.errorprone:error_prone_annotations:jar:2.21.1:compile
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  \- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- com.pig4cloud:pig-common-security:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-extra:jar:5.8.36:compile
[INFO] |  |  \- cn.hutool:hutool-setting:jar:5.8.36:compile
[INFO] |  |     \- cn.hutool:hutool-log:jar:5.8.36:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-jose:jar:6.4.4:compile
[INFO] |  |  \- com.nimbusds:nimbus-jose-jwt:jar:9.37.3:compile
[INFO] |  |     \- com.github.stephenc.jcip:jcip-annotations:jar:1.0-1:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-authorization-server:jar:1.4.2:compile
[INFO] |  |  +- org.springframework.security:spring-security-config:jar:6.4.4:compile
[INFO] |  |  +- org.springframework.security:spring-security-web:jar:6.4.4:compile
[INFO] |  |  \- org.springframework.security:spring-security-oauth2-resource-server:jar:6.4.4:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-aop:jar:3.4.4:compile
[INFO] |     \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] +- com.pig4cloud:pig-common-feign:jar:3.8.3:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-transport-simple-http:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-transport-common:jar:1.8.8:compile
[INFO] |  |  |     \- com.alibaba.csp:sentinel-datasource-extension:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-annotation-aspectj:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-core:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.cloud:spring-cloud-circuitbreaker-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-reactor-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webflux-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webmvc-v6x-adapter:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-web-adapter-common:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-parameter-flow-control:jar:1.8.8:compile
[INFO] |  |  |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-server-default:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-cluster-common-default:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-client-default:jar:1.8.8:compile
[INFO] |  |  \- com.alibaba.cloud:spring-cloud-alibaba-sentinel-datasource:jar:2023.0.3.2:compile
[INFO] |  +- io.github.openfeign:feign-okhttp:jar:13.5:compile
[INFO] |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:compile
[INFO] |  |     +- com.squareup.okio:okio:jar:3.6.0:compile
[INFO] |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:compile
[INFO] |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:compile
[INFO] |  |        |  \- org.jetbrains:annotations:jar:13.0:compile
[INFO] |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:compile
[INFO] |  \- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |     +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |     +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |     \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] +- org.springdoc:springdoc-openapi-starter-webflux-ui:jar:2.8.6:compile
[INFO] |  +- org.springdoc:springdoc-openapi-starter-webflux-api:jar:2.8.6:compile
[INFO] |  |  \- org.springdoc:springdoc-openapi-starter-common:jar:2.8.6:compile
[INFO] |  |     \- io.swagger.core.v3:swagger-core-jakarta:jar:2.2.29:compile
[INFO] |  |        +- io.swagger.core.v3:swagger-models-jakarta:jar:2.2.29:compile
[INFO] |  |        \- com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:jar:2.18.3:compile
[INFO] |  +- org.webjars:swagger-ui:jar:5.20.1:compile
[INFO] |  \- org.webjars:webjars-locator-lite:jar:1.0.1:compile
[INFO] |     \- org.jspecify:jspecify:jar:1.0.0:compile
[INFO] +- io.springboot:knife4j-openapi3-ui:jar:3.0.5:compile
[INFO] +- cn.hutool:hutool-crypto:jar:5.8.36:compile
[INFO] +- io.github.openfeign:feign-slf4j:jar:13.5:compile
[INFO] |  \- io.github.openfeign:feign-core:jar:13.5:compile
[INFO] +- org.springframework.cloud:spring-cloud-openfeign-core:jar:4.2.1:compile
[INFO] |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  \- io.github.openfeign:feign-form-spring:jar:13.5:compile
[INFO] |     +- io.github.openfeign:feign-form:jar:13.5:compile
[INFO] |     \- commons-fileupload:commons-fileupload:jar:1.5:compile
[INFO] +- org.springframework.cloud:spring-cloud-starter-openfeign:jar:4.2.1:compile
[INFO] |  \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-starter-webflux:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-reactor-netty:jar:3.4.4:compile
[INFO] |  |  \- io.projectreactor.netty:reactor-netty-http:jar:1.2.4:compile
[INFO] |  |     +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |     |  \- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |     +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:compile
[INFO] |  |     |  \- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |     +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:compile
[INFO] |  |     |  \- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |     \- io.projectreactor.netty:reactor-netty-core:jar:1.2.4:compile
[INFO] |  \- org.springframework:spring-webflux:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO] |  +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO] |  +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO] |  +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO] |  +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO] |  |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO] |  +- net.minidev:json-smart:jar:2.5.2:test
[INFO] |  |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO] |  |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO] |  +- org.assertj:assertj-core:jar:3.26.3:test
[INFO] |  |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO] |  +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO] |  +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO] |  +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO] |  |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO] |  |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO] |  |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO] |  |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO] |  |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO] |  |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO] |  |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO] |  +- org.mockito:mockito-core:jar:5.14.2:test
[INFO] |  |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO] |  |  \- org.objenesis:objenesis:jar:3.3:test
[INFO] |  +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO] |  +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO] |  |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO] |  +- org.springframework:spring-core:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-test:jar:6.2.5:test
[INFO] |  \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] +- io.projectreactor:reactor-test:jar:3.7.4:test
[INFO] +- com.pig4cloud:pig-common-log:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-http:jar:5.8.36:compile
[INFO] |  \- org.springframework.security:spring-security-oauth2-core:jar:6.4.4:compile
[INFO] +- com.pig4cloud:pig-upms-api:jar:3.8.3:compile
[INFO] |  +- com.pig4cloud:pig-common-mybatis:jar:3.8.3:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-spring:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-jsqlparser:jar:3.5.11:compile
[INFO] |  |  |  +- com.github.jsqlparser:jsqlparser:jar:5.1:compile
[INFO] |  |  |  \- com.baomidou:mybatis-plus-jsqlparser-common:jar:3.5.11:compile
[INFO] |  |  \- io.swagger.core.v3:swagger-annotations-jakarta:jar:2.2.29:compile
[INFO] |  \- com.pig4cloud:pig-common-excel:jar:3.8.3:compile
[INFO] |     \- com.pig4cloud.excel:excel-spring-boot-starter:jar:3.4.0:compile
[INFO] |        +- cn.idev.excel:fastexcel:jar:1.0.0:compile
[INFO] |        |  \- cn.idev.excel:fastexcel-core:jar:1.0.0:compile
[INFO] |        |     +- org.apache.commons:commons-csv:jar:1.11.0:compile
[INFO] |        |     +- org.apache.poi:poi:jar:5.2.5:compile
[INFO] |        |     |  +- org.apache.commons:commons-collections4:jar:4.4:compile
[INFO] |        |     |  +- org.apache.commons:commons-math3:jar:3.6.1:compile
[INFO] |        |     |  \- com.zaxxer:SparseBitSet:jar:1.3:compile
[INFO] |        |     +- org.apache.poi:poi-ooxml:jar:5.2.5:compile
[INFO] |        |     |  +- org.apache.poi:poi-ooxml-lite:jar:5.2.5:compile
[INFO] |        |     |  +- org.apache.xmlbeans:xmlbeans:jar:5.2.0:compile
[INFO] |        |     |  \- com.github.virtuald:curvesapi:jar:1.08:compile
[INFO] |        |     +- org.ehcache:ehcache:jar:3.10.8:compile
[INFO] |        |     |  +- javax.cache:cache-api:jar:1.1.1:compile
[INFO] |        |     |  \- org.glassfish.jaxb:jaxb-runtime:jar:4.0.5:runtime
[INFO] |        |     |     \- org.glassfish.jaxb:jaxb-core:jar:4.0.5:runtime
[INFO] |        |     |        +- org.glassfish.jaxb:txw2:jar:4.0.5:runtime
[INFO] |        |     |        \- com.sun.istack:istack-commons-runtime:jar:4.1.2:runtime
[INFO] |        |     +- cn.idev.excel:fastexcel-support:jar:1.0.0:compile
[INFO] |        |     +- com.itextpdf:itext7-core:pom:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:barcodes:jar:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:hyph:jar:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:sign:jar:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:styled-xml-parser:jar:7.1.15:compile
[INFO] |        |     |  \- com.itextpdf:svg:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:kernel:jar:7.1.15:compile
[INFO] |        |     |  +- org.bouncycastle:bcpkix-jdk15on:jar:1.68:compile
[INFO] |        |     |  \- org.bouncycastle:bcprov-jdk15on:jar:1.68:compile
[INFO] |        |     +- com.itextpdf:io:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:layout:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:forms:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:pdfa:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:pdftest:jar:7.1.15:compile
[INFO] |        |     |  +- junit:junit:jar:4.13.2:compile
[INFO] |        |     |  |  \- org.hamcrest:hamcrest-core:jar:2.2:compile
[INFO] |        |     |  \- org.verapdf:validation-model:jar:1.16.1:compile
[INFO] |        |     |     +- org.verapdf:parser:jar:1.16.1:compile
[INFO] |        |     |     +- org.verapdf:pdf-model:jar:1.16.1:compile
[INFO] |        |     |     +- org.verapdf:core:jar:1.16.1:compile
[INFO] |        |     |     |  +- org.verapdf:verapdf-xmp-core:jar:1.16.1:compile
[INFO] |        |     |     |  +- org.mozilla:rhino:jar:1.7.11:compile
[INFO] |        |     |     |  +- javax.xml.bind:jaxb-api:jar:2.4.0-b180830.0359:compile
[INFO] |        |     |     |  |  \- javax.activation:javax.activation-api:jar:1.2.0:compile
[INFO] |        |     |     |  \- net.java.dev.stax-utils:stax-utils:jar:20070216:compile
[INFO] |        |     |     +- org.verapdf:feature-reporting:jar:1.16.1:compile
[INFO] |        |     |     \- org.verapdf:metadata-fixer:jar:1.16.1:compile
[INFO] |        |     \- com.itextpdf:font-asian:jar:7.1.15:compile
[INFO] |        \- org.apache.commons:commons-compress:jar:1.27.1:compile
[INFO] |           \- commons-io:commons-io:jar:2.18.0:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] \- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO]    \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO]       \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] 
[INFO] -----------------------< com.pig4cloud:pig-auth >-----------------------
[INFO] Building pig-auth 3.8.3                                          [13/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-auth ---
[INFO] com.pig4cloud:pig-auth:jar:3.8.3
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.nacos:nacos-client:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-auth-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-encryption-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-logback-adapter-12:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:logback-adapter:jar:1.1.3:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-log4j2-adapter:jar:2.4.2:compile
[INFO] |  |  +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  +- org.apache.httpcomponents:httpasyncclient:jar:4.1.5:compile
[INFO] |  |  |  +- org.apache.httpcomponents:httpcore-nio:jar:4.4.16:compile
[INFO] |  |  |  \- org.apache.httpcomponents:httpclient:jar:4.5.13:compile
[INFO] |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.16:compile
[INFO] |  |  +- io.prometheus:simpleclient:jar:0.16.0:compile
[INFO] |  |  |  +- io.prometheus:simpleclient_tracer_otel:jar:0.16.0:compile
[INFO] |  |  |  |  \- io.prometheus:simpleclient_tracer_common:jar:0.16.0:compile
[INFO] |  |  |  \- io.prometheus:simpleclient_tracer_otel_agent:jar:0.16.0:compile
[INFO] |  |  +- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  |  \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |  |     +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |  |     \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  \- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  |  \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |  +- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] |  \- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] +- com.pig4cloud:pig-common-feign:jar:3.8.3:compile
[INFO] |  +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |  |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  |  |     \- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |  |        |  \- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-transport-simple-http:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-transport-common:jar:1.8.8:compile
[INFO] |  |  |     +- com.alibaba.csp:sentinel-datasource-extension:jar:1.8.8:compile
[INFO] |  |  |     \- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-annotation-aspectj:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-core:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.cloud:spring-cloud-circuitbreaker-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-reactor-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webflux-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webmvc-v6x-adapter:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-web-adapter-common:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-parameter-flow-control:jar:1.8.8:compile
[INFO] |  |  |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-server-default:jar:1.8.8:compile
[INFO] |  |  |  +- com.alibaba.csp:sentinel-cluster-common-default:jar:1.8.8:compile
[INFO] |  |  |  \- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |     \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-client-default:jar:1.8.8:compile
[INFO] |  |  \- com.alibaba.cloud:spring-cloud-alibaba-sentinel-datasource:jar:2023.0.3.2:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-openfeign:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-starter:jar:4.2.1:compile
[INFO] |  |  |  \- org.bouncycastle:bcprov-jdk18on:jar:1.78.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-openfeign-core:jar:4.2.1:compile
[INFO] |  |  |  \- io.github.openfeign:feign-form-spring:jar:13.5:compile
[INFO] |  |  |     +- io.github.openfeign:feign-form:jar:13.5:compile
[INFO] |  |  |     \- commons-fileupload:commons-fileupload:jar:1.5:compile
[INFO] |  |  +- io.github.openfeign:feign-core:jar:13.5:compile
[INFO] |  |  \- io.github.openfeign:feign-slf4j:jar:13.5:compile
[INFO] |  +- io.github.openfeign:feign-okhttp:jar:13.5:compile
[INFO] |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:compile
[INFO] |  |     +- com.squareup.okio:okio:jar:3.6.0:compile
[INFO] |  |     |  \- com.squareup.okio:okio-jvm:jar:3.6.0:compile
[INFO] |  |     |     \- org.jetbrains.kotlin:kotlin-stdlib-common:jar:1.9.25:compile
[INFO] |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:compile
[INFO] |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:compile
[INFO] |  |        |  \- org.jetbrains:annotations:jar:13.0:compile
[INFO] |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-loadbalancer:jar:4.2.1:compile
[INFO] |  |  |  +- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |  |  \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  |  \- io.projectreactor.addons:reactor-extra:jar:3.5.2:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-cache:jar:3.4.4:compile
[INFO] |  |  \- com.stoyanr:evictor:jar:1.0.0:compile
[INFO] |  +- com.github.ben-manes.caffeine:caffeine:jar:3.1.8:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:3.37.0:compile
[INFO] |  |  \- com.google.errorprone:error_prone_annotations:jar:2.21.1:compile
[INFO] |  +- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |  |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  \- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] +- com.pig4cloud:pig-upms-api:jar:3.8.3:compile
[INFO] |  +- com.pig4cloud:pig-common-mybatis:jar:3.8.3:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-spring:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-jsqlparser:jar:3.5.11:compile
[INFO] |  |  |  +- com.github.jsqlparser:jsqlparser:jar:5.1:compile
[INFO] |  |  |  \- com.baomidou:mybatis-plus-jsqlparser-common:jar:3.5.11:compile
[INFO] |  |  \- io.swagger.core.v3:swagger-annotations-jakarta:jar:2.2.29:compile
[INFO] |  \- com.pig4cloud:pig-common-excel:jar:3.8.3:compile
[INFO] |     \- com.pig4cloud.excel:excel-spring-boot-starter:jar:3.4.0:compile
[INFO] |        +- cn.idev.excel:fastexcel:jar:1.0.0:compile
[INFO] |        |  \- cn.idev.excel:fastexcel-core:jar:1.0.0:compile
[INFO] |        |     +- org.apache.commons:commons-csv:jar:1.11.0:compile
[INFO] |        |     +- org.apache.poi:poi:jar:5.2.5:compile
[INFO] |        |     |  +- org.apache.commons:commons-collections4:jar:4.4:compile
[INFO] |        |     |  +- org.apache.commons:commons-math3:jar:3.6.1:compile
[INFO] |        |     |  \- com.zaxxer:SparseBitSet:jar:1.3:compile
[INFO] |        |     +- org.apache.poi:poi-ooxml:jar:5.2.5:compile
[INFO] |        |     |  +- org.apache.poi:poi-ooxml-lite:jar:5.2.5:compile
[INFO] |        |     |  +- org.apache.xmlbeans:xmlbeans:jar:5.2.0:compile
[INFO] |        |     |  \- com.github.virtuald:curvesapi:jar:1.08:compile
[INFO] |        |     +- org.ehcache:ehcache:jar:3.10.8:compile
[INFO] |        |     |  +- javax.cache:cache-api:jar:1.1.1:compile
[INFO] |        |     |  \- org.glassfish.jaxb:jaxb-runtime:jar:4.0.5:runtime
[INFO] |        |     |     \- org.glassfish.jaxb:jaxb-core:jar:4.0.5:runtime
[INFO] |        |     |        +- org.glassfish.jaxb:txw2:jar:4.0.5:runtime
[INFO] |        |     |        \- com.sun.istack:istack-commons-runtime:jar:4.1.2:runtime
[INFO] |        |     +- cn.idev.excel:fastexcel-support:jar:1.0.0:compile
[INFO] |        |     +- com.itextpdf:itext7-core:pom:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:barcodes:jar:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:hyph:jar:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:sign:jar:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:styled-xml-parser:jar:7.1.15:compile
[INFO] |        |     |  \- com.itextpdf:svg:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:kernel:jar:7.1.15:compile
[INFO] |        |     |  +- org.bouncycastle:bcpkix-jdk15on:jar:1.68:compile
[INFO] |        |     |  \- org.bouncycastle:bcprov-jdk15on:jar:1.68:compile
[INFO] |        |     +- com.itextpdf:io:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:layout:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:forms:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:pdfa:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:pdftest:jar:7.1.15:compile
[INFO] |        |     |  +- junit:junit:jar:4.13.2:compile
[INFO] |        |     |  |  \- org.hamcrest:hamcrest-core:jar:2.2:compile
[INFO] |        |     |  \- org.verapdf:validation-model:jar:1.16.1:compile
[INFO] |        |     |     +- org.verapdf:parser:jar:1.16.1:compile
[INFO] |        |     |     +- org.verapdf:pdf-model:jar:1.16.1:compile
[INFO] |        |     |     +- org.verapdf:core:jar:1.16.1:compile
[INFO] |        |     |     |  +- org.verapdf:verapdf-xmp-core:jar:1.16.1:compile
[INFO] |        |     |     |  +- org.mozilla:rhino:jar:1.7.11:compile
[INFO] |        |     |     |  +- javax.xml.bind:jaxb-api:jar:2.4.0-b180830.0359:compile
[INFO] |        |     |     |  |  \- javax.activation:javax.activation-api:jar:1.2.0:compile
[INFO] |        |     |     |  \- net.java.dev.stax-utils:stax-utils:jar:20070216:compile
[INFO] |        |     |     +- org.verapdf:feature-reporting:jar:1.16.1:compile
[INFO] |        |     |     \- org.verapdf:metadata-fixer:jar:1.16.1:compile
[INFO] |        |     \- com.itextpdf:font-asian:jar:7.1.15:compile
[INFO] |        \- org.apache.commons:commons-compress:jar:1.27.1:compile
[INFO] |           \- commons-io:commons-io:jar:2.18.0:compile
[INFO] +- com.pig4cloud:pig-common-security:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-extra:jar:5.8.36:compile
[INFO] |  |  \- cn.hutool:hutool-setting:jar:5.8.36:compile
[INFO] |  |     \- cn.hutool:hutool-log:jar:5.8.36:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-jose:jar:6.4.4:compile
[INFO] |  |  \- com.nimbusds:nimbus-jose-jwt:jar:9.37.3:compile
[INFO] |  |     \- com.github.stephenc.jcip:jcip-annotations:jar:1.0-1:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-authorization-server:jar:1.4.2:compile
[INFO] |  |  \- org.springframework.security:spring-security-oauth2-resource-server:jar:6.4.4:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-aop:jar:3.4.4:compile
[INFO] |     \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] +- org.springframework.boot:spring-boot-starter-security:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |     +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |     |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |     +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |     |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |     \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  +- org.springframework.security:spring-security-config:jar:6.4.4:compile
[INFO] |  \- org.springframework.security:spring-security-web:jar:6.4.4:compile
[INFO] +- org.springframework.boot:spring-boot-starter-freemarker:jar:3.4.4:compile
[INFO] |  +- org.freemarker:freemarker:jar:2.3.34:compile
[INFO] |  \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-starter-undertow:jar:3.4.4:compile
[INFO] |  +- io.undertow:undertow-core:jar:2.3.18.Final:compile
[INFO] |  |  +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |  +- org.jboss.xnio:xnio-api:jar:3.8.16.Final:compile
[INFO] |  |  |  +- org.wildfly.common:wildfly-common:jar:1.5.4.Final:compile
[INFO] |  |  |  \- org.wildfly.client:wildfly-client-config:jar:1.0.1.Final:compile
[INFO] |  |  +- org.jboss.xnio:xnio-nio:jar:3.8.16.Final:runtime
[INFO] |  |  \- org.jboss.threads:jboss-threads:jar:3.5.0.Final:compile
[INFO] |  +- io.undertow:undertow-servlet:jar:2.3.18.Final:compile
[INFO] |  +- io.undertow:undertow-websockets-jsr:jar:2.3.18.Final:compile
[INFO] |  |  +- jakarta.websocket:jakarta.websocket-api:jar:2.1.1:compile
[INFO] |  |  \- jakarta.websocket:jakarta.websocket-client-api:jar:2.1.1:compile
[INFO] |  \- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] +- com.pig4cloud:pig-common-log:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-http:jar:5.8.36:compile
[INFO] |  \- org.springframework.security:spring-security-oauth2-core:jar:6.4.4:compile
[INFO] +- com.pig4cloud.plugin:captcha-core:jar:2.2.5:compile
[INFO] |  +- com.googlecode.aviator:aviator:jar:5.3.0:compile
[INFO] |  +- javax.servlet:javax.servlet-api:jar:4.0.1:compile
[INFO] |  \- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] +- cn.hutool:hutool-crypto:jar:5.8.36:compile
[INFO] |  \- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  |  \- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] --------------------< com.pig4cloud:pig-common-oss >--------------------
[INFO] Building pig-common-oss 3.8.3                                    [14/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-oss ---
[INFO] com.pig4cloud:pig-common-oss:jar:3.8.3
[INFO] +- com.amazonaws:aws-java-sdk-s3:jar:1.12.675:compile
[INFO] |  +- com.amazonaws:aws-java-sdk-kms:jar:1.12.675:compile
[INFO] |  +- com.amazonaws:aws-java-sdk-core:jar:1.12.675:compile
[INFO] |  |  +- commons-logging:commons-logging:jar:1.1.3:compile
[INFO] |  |  +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |  +- org.apache.httpcomponents:httpclient:jar:4.5.13:compile
[INFO] |  |  |  \- org.apache.httpcomponents:httpcore:jar:4.4.16:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:jar:2.18.3:compile
[INFO] |  |  \- joda-time:joda-time:jar:2.8.1:compile
[INFO] |  \- com.amazonaws:jmespath-java:jar:1.12.675:compile
[INFO] +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  |  \- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  |     +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  |     \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  |  \- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:runtime
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] |     \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |        \- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] ------------------< com.pig4cloud:pig-common-swagger >------------------
[INFO] Building pig-common-swagger 3.8.3                                [15/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-swagger ---
[INFO] com.pig4cloud:pig-common-swagger:jar:3.8.3
[INFO] +- org.springdoc:springdoc-openapi-starter-webmvc-api:jar:2.8.6:compile
[INFO] |  \- org.springdoc:springdoc-openapi-starter-common:jar:2.8.6:compile
[INFO] |     +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |     \- io.swagger.core.v3:swagger-core-jakarta:jar:2.2.29:compile
[INFO] |        +- io.swagger.core.v3:swagger-annotations-jakarta:jar:2.2.29:compile
[INFO] |        +- io.swagger.core.v3:swagger-models-jakarta:jar:2.2.29:compile
[INFO] |        +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |        +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |        \- com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:jar:2.18.3:compile
[INFO] +- org.springframework:spring-webflux:jar:6.2.5:provided
[INFO] |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-core:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |  \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |     \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] +- org.springframework.cloud:spring-cloud-gateway-server:jar:4.2.1:provided
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  \- io.projectreactor.addons:reactor-extra:jar:3.5.2:provided
[INFO] +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:provided
[INFO] |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:provided
[INFO] +- org.springframework:spring-webmvc:jar:6.2.5:provided
[INFO] |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2023.0.3.2:provided
[INFO] |  +- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2023.0.3.2:provided
[INFO] |  +- com.alibaba.nacos:nacos-client:jar:2.4.2:provided
[INFO] |  |  +- com.alibaba.nacos:nacos-auth-plugin:jar:2.4.2:provided
[INFO] |  |  +- com.alibaba.nacos:nacos-encryption-plugin:jar:2.4.2:provided
[INFO] |  |  +- com.alibaba.nacos:nacos-logback-adapter-12:jar:2.4.2:provided
[INFO] |  |  +- com.alibaba.nacos:logback-adapter:jar:1.1.3:provided
[INFO] |  |  +- com.alibaba.nacos:nacos-log4j2-adapter:jar:2.4.2:provided
[INFO] |  |  +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  +- org.apache.httpcomponents:httpasyncclient:jar:4.1.5:provided
[INFO] |  |  |  +- org.apache.httpcomponents:httpcore-nio:jar:4.4.16:provided
[INFO] |  |  |  \- org.apache.httpcomponents:httpclient:jar:4.5.13:provided
[INFO] |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.16:provided
[INFO] |  |  +- io.prometheus:simpleclient:jar:0.16.0:provided
[INFO] |  |  |  +- io.prometheus:simpleclient_tracer_otel:jar:0.16.0:provided
[INFO] |  |  |  |  \- io.prometheus:simpleclient_tracer_common:jar:0.16.0:provided
[INFO] |  |  |  \- io.prometheus:simpleclient_tracer_otel_agent:jar:0.16.0:provided
[INFO] |  |  \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |  |     +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |  |     \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] |  \- org.springframework.cloud:spring-cloud-context:jar:4.2.1:provided
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |     \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  +- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:runtime
[INFO] |  |  |  |  |  |     +- com.squareup.okio:okio:jar:3.6.0:runtime
[INFO] |  |  |  |  |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        |  \- org.jetbrains:annotations:jar:13.0:runtime
[INFO] |  |  |  |  |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.14.0:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] --------------------< com.pig4cloud:pig-common-xss >--------------------
[INFO] Building pig-common-xss 3.8.3                                    [16/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-xss ---
[INFO] com.pig4cloud:pig-common-xss:jar:3.8.3
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |     \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |     \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  +- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |        +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:runtime
[INFO] |  |  |  |  |  |     +- com.squareup.okio:okio:jar:3.6.0:runtime
[INFO] |  |  |  |  |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        |  \- org.jetbrains:annotations:jar:13.0:runtime
[INFO] |  |  |  |  |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.14.0:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- org.jsoup:jsoup:jar:1.18.3:compile
[INFO] +- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-core:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:provided
[INFO] +- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] ---------------------< com.pig4cloud:pig-upms-biz >---------------------
[INFO] Building pig-upms-biz 3.8.3                                      [17/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-upms-biz ---
[INFO] com.pig4cloud:pig-upms-biz:jar:3.8.3
[INFO] +- com.pig4cloud:pig-upms-api:jar:3.8.3:compile
[INFO] |  +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |  |     \- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |  +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |  |        |  \- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  |  \- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  +- com.pig4cloud:pig-common-mybatis:jar:3.8.3:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-spring:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-jsqlparser:jar:3.5.11:compile
[INFO] |  |  |  +- com.github.jsqlparser:jsqlparser:jar:5.1:compile
[INFO] |  |  |  \- com.baomidou:mybatis-plus-jsqlparser-common:jar:3.5.11:compile
[INFO] |  |  \- io.swagger.core.v3:swagger-annotations-jakarta:jar:2.2.29:compile
[INFO] |  \- com.pig4cloud:pig-common-excel:jar:3.8.3:compile
[INFO] |     \- com.pig4cloud.excel:excel-spring-boot-starter:jar:3.4.0:compile
[INFO] |        +- cn.idev.excel:fastexcel:jar:1.0.0:compile
[INFO] |        |  \- cn.idev.excel:fastexcel-core:jar:1.0.0:compile
[INFO] |        |     +- org.apache.commons:commons-csv:jar:1.11.0:compile
[INFO] |        |     +- org.apache.poi:poi:jar:5.2.5:compile
[INFO] |        |     |  +- org.apache.commons:commons-collections4:jar:4.4:compile
[INFO] |        |     |  +- org.apache.commons:commons-math3:jar:3.6.1:compile
[INFO] |        |     |  \- com.zaxxer:SparseBitSet:jar:1.3:compile
[INFO] |        |     +- org.apache.poi:poi-ooxml:jar:5.2.5:compile
[INFO] |        |     |  +- org.apache.poi:poi-ooxml-lite:jar:5.2.5:compile
[INFO] |        |     |  +- org.apache.xmlbeans:xmlbeans:jar:5.2.0:compile
[INFO] |        |     |  \- com.github.virtuald:curvesapi:jar:1.08:compile
[INFO] |        |     +- org.ehcache:ehcache:jar:3.10.8:compile
[INFO] |        |     |  \- javax.cache:cache-api:jar:1.1.1:compile
[INFO] |        |     +- cn.idev.excel:fastexcel-support:jar:1.0.0:compile
[INFO] |        |     +- com.itextpdf:itext7-core:pom:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:barcodes:jar:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:hyph:jar:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:sign:jar:7.1.15:compile
[INFO] |        |     |  +- com.itextpdf:styled-xml-parser:jar:7.1.15:compile
[INFO] |        |     |  \- com.itextpdf:svg:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:kernel:jar:7.1.15:compile
[INFO] |        |     |  \- org.bouncycastle:bcpkix-jdk15on:jar:1.68:compile
[INFO] |        |     +- com.itextpdf:io:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:layout:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:forms:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:pdfa:jar:7.1.15:compile
[INFO] |        |     +- com.itextpdf:pdftest:jar:7.1.15:compile
[INFO] |        |     |  +- junit:junit:jar:4.13.2:compile
[INFO] |        |     |  |  \- org.hamcrest:hamcrest-core:jar:2.2:compile
[INFO] |        |     |  \- org.verapdf:validation-model:jar:1.16.1:compile
[INFO] |        |     |     +- org.verapdf:parser:jar:1.16.1:compile
[INFO] |        |     |     +- org.verapdf:pdf-model:jar:1.16.1:compile
[INFO] |        |     |     +- org.verapdf:core:jar:1.16.1:compile
[INFO] |        |     |     |  +- org.verapdf:verapdf-xmp-core:jar:1.16.1:compile
[INFO] |        |     |     |  +- org.mozilla:rhino:jar:1.7.11:compile
[INFO] |        |     |     |  \- net.java.dev.stax-utils:stax-utils:jar:20070216:compile
[INFO] |        |     |     +- org.verapdf:feature-reporting:jar:1.16.1:compile
[INFO] |        |     |     \- org.verapdf:metadata-fixer:jar:1.16.1:compile
[INFO] |        |     \- com.itextpdf:font-asian:jar:7.1.15:compile
[INFO] |        \- org.apache.commons:commons-compress:jar:1.27.1:compile
[INFO] |           \- commons-io:commons-io:jar:2.18.0:compile
[INFO] +- com.pig4cloud:pig-common-oss:jar:3.8.3:compile
[INFO] |  +- com.amazonaws:aws-java-sdk-s3:jar:1.12.675:compile
[INFO] |  |  +- com.amazonaws:aws-java-sdk-kms:jar:1.12.675:compile
[INFO] |  |  +- com.amazonaws:aws-java-sdk-core:jar:1.12.675:compile
[INFO] |  |  |  +- com.fasterxml.jackson.dataformat:jackson-dataformat-cbor:jar:2.18.3:compile
[INFO] |  |  |  \- joda-time:joda-time:jar:2.8.1:compile
[INFO] |  |  \- com.amazonaws:jmespath-java:jar:1.12.675:compile
[INFO] |  \- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] +- com.pig4cloud:pig-common-feign:jar:3.8.3:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-transport-simple-http:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-transport-common:jar:1.8.8:compile
[INFO] |  |  |     +- com.alibaba.csp:sentinel-datasource-extension:jar:1.8.8:compile
[INFO] |  |  |     \- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-annotation-aspectj:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-core:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.cloud:spring-cloud-circuitbreaker-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-reactor-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webflux-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webmvc-v6x-adapter:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-web-adapter-common:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-parameter-flow-control:jar:1.8.8:compile
[INFO] |  |  |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-server-default:jar:1.8.8:compile
[INFO] |  |  |  +- com.alibaba.csp:sentinel-cluster-common-default:jar:1.8.8:compile
[INFO] |  |  |  \- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |     \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-client-default:jar:1.8.8:compile
[INFO] |  |  \- com.alibaba.cloud:spring-cloud-alibaba-sentinel-datasource:jar:2023.0.3.2:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-openfeign:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-starter:jar:4.2.1:compile
[INFO] |  |  |  \- org.bouncycastle:bcprov-jdk18on:jar:1.78.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-openfeign-core:jar:4.2.1:compile
[INFO] |  |  |  \- io.github.openfeign:feign-form-spring:jar:13.5:compile
[INFO] |  |  |     +- io.github.openfeign:feign-form:jar:13.5:compile
[INFO] |  |  |     \- commons-fileupload:commons-fileupload:jar:1.5:compile
[INFO] |  |  +- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |  |  +- io.github.openfeign:feign-core:jar:13.5:compile
[INFO] |  |  \- io.github.openfeign:feign-slf4j:jar:13.5:compile
[INFO] |  +- io.github.openfeign:feign-okhttp:jar:13.5:compile
[INFO] |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:compile
[INFO] |  |     +- com.squareup.okio:okio:jar:3.6.0:compile
[INFO] |  |     |  \- com.squareup.okio:okio-jvm:jar:3.6.0:compile
[INFO] |  |     |     \- org.jetbrains.kotlin:kotlin-stdlib-common:jar:1.9.25:compile
[INFO] |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:compile
[INFO] |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:compile
[INFO] |  |        |  \- org.jetbrains:annotations:jar:13.0:compile
[INFO] |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-loadbalancer:jar:4.2.1:compile
[INFO] |  |  |  +- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |  |  \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  |  \- io.projectreactor.addons:reactor-extra:jar:3.5.2:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-cache:jar:3.4.4:compile
[INFO] |  |  |  \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  |  \- com.stoyanr:evictor:jar:1.0.0:compile
[INFO] |  +- com.github.ben-manes.caffeine:caffeine:jar:3.1.8:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:3.37.0:compile
[INFO] |  |  \- com.google.errorprone:error_prone_annotations:jar:2.21.1:compile
[INFO] |  +- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |  |  +- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  \- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] +- com.pig4cloud:pig-common-security:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-extra:jar:5.8.36:compile
[INFO] |  |  \- cn.hutool:hutool-setting:jar:5.8.36:compile
[INFO] |  |     \- cn.hutool:hutool-log:jar:5.8.36:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-jose:jar:6.4.4:compile
[INFO] |  |  \- com.nimbusds:nimbus-jose-jwt:jar:9.37.3:compile
[INFO] |  |     \- com.github.stephenc.jcip:jcip-annotations:jar:1.0-1:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-authorization-server:jar:1.4.2:compile
[INFO] |  |  +- org.springframework.security:spring-security-config:jar:6.4.4:compile
[INFO] |  |  +- org.springframework.security:spring-security-web:jar:6.4.4:compile
[INFO] |  |  \- org.springframework.security:spring-security-oauth2-resource-server:jar:6.4.4:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-aop:jar:3.4.4:compile
[INFO] |     \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] +- com.pig4cloud:pig-common-log:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-http:jar:5.8.36:compile
[INFO] |  \- org.springframework.security:spring-security-oauth2-core:jar:6.4.4:compile
[INFO] +- com.pig4cloud:pig-common-swagger:jar:3.8.3:compile
[INFO] |  \- org.springdoc:springdoc-openapi-starter-webmvc-api:jar:2.8.6:compile
[INFO] |     \- org.springdoc:springdoc-openapi-starter-common:jar:2.8.6:compile
[INFO] |        \- io.swagger.core.v3:swagger-core-jakarta:jar:2.2.29:compile
[INFO] |           +- io.swagger.core.v3:swagger-models-jakarta:jar:2.2.29:compile
[INFO] |           \- com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:jar:2.18.3:compile
[INFO] +- com.baomidou:mybatis-plus-spring-boot3-starter:jar:3.5.11:compile
[INFO] |  +- com.baomidou:mybatis-plus:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- org.mybatis:mybatis-spring:jar:3.0.4:compile
[INFO] |  +- com.baomidou:mybatis-plus-spring-boot-autoconfigure:jar:3.5.11:compile
[INFO] |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-jdbc:jar:3.4.4:compile
[INFO] |     +- com.zaxxer:HikariCP:jar:5.1.0:compile
[INFO] |     \- org.springframework:spring-jdbc:jar:6.2.5:compile
[INFO] |        \- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] +- com.mysql:mysql-connector-j:jar:9.2.0:compile
[INFO] |  \- com.google.protobuf:protobuf-java:jar:4.29.0:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.nacos:nacos-client:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-auth-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-encryption-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-logback-adapter-12:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:logback-adapter:jar:1.1.3:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-log4j2-adapter:jar:2.4.2:compile
[INFO] |  |  +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- org.apache.httpcomponents:httpasyncclient:jar:4.1.5:compile
[INFO] |  |  |  \- org.apache.httpcomponents:httpcore-nio:jar:4.4.16:compile
[INFO] |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.16:compile
[INFO] |  |  +- io.prometheus:simpleclient:jar:0.16.0:compile
[INFO] |  |  |  +- io.prometheus:simpleclient_tracer_otel:jar:0.16.0:compile
[INFO] |  |  |  |  \- io.prometheus:simpleclient_tracer_common:jar:0.16.0:compile
[INFO] |  |  |  \- io.prometheus:simpleclient_tracer_otel_agent:jar:0.16.0:compile
[INFO] |  |  +- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  |  \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |  |     +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |  |     \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] |  \- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] |  \- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] +- io.springboot.sms:aliyun-sms-spring-boot-starter:jar:3.0.0:compile
[INFO] |  \- com.aliyun:aliyun-java-sdk-core:jar:4.6.3:compile
[INFO] |     +- com.google.code.gson:gson:jar:2.11.0:compile
[INFO] |     +- org.apache.httpcomponents:httpclient:jar:4.5.13:compile
[INFO] |     +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |     +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |     +- javax.xml.bind:jaxb-api:jar:2.3.1:compile
[INFO] |     |  \- javax.activation:javax.activation-api:jar:1.2.0:compile
[INFO] |     +- org.glassfish.jaxb:jaxb-runtime:jar:4.0.5:compile
[INFO] |     |  \- org.glassfish.jaxb:jaxb-core:jar:4.0.5:compile
[INFO] |     |     +- org.glassfish.jaxb:txw2:jar:4.0.5:compile
[INFO] |     |     \- com.sun.istack:istack-commons-runtime:jar:4.1.2:compile
[INFO] |     +- org.bouncycastle:bcprov-jdk15on:jar:1.70:compile
[INFO] |     +- org.jacoco:org.jacoco.agent:jar:runtime:0.8.8:compile
[INFO] |     +- org.ini4j:ini4j:jar:0.5.4:compile
[INFO] |     +- io.opentracing:opentracing-api:jar:0.33.0:compile
[INFO] |     \- io.opentracing:opentracing-util:jar:0.33.0:compile
[INFO] |        \- io.opentracing:opentracing-noop:jar:0.33.0:compile
[INFO] +- com.pig4cloud:pig-common-xss:jar:3.8.3:compile
[INFO] |  +- org.jsoup:jsoup:jar:1.18.3:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |     \- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-undertow:jar:3.4.4:compile
[INFO] |  +- io.undertow:undertow-core:jar:2.3.18.Final:compile
[INFO] |  |  +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |  +- org.jboss.xnio:xnio-api:jar:3.8.16.Final:compile
[INFO] |  |  |  +- org.wildfly.common:wildfly-common:jar:1.5.4.Final:compile
[INFO] |  |  |  \- org.wildfly.client:wildfly-client-config:jar:1.0.1.Final:compile
[INFO] |  |  +- org.jboss.xnio:xnio-nio:jar:3.8.16.Final:runtime
[INFO] |  |  \- org.jboss.threads:jboss-threads:jar:3.5.0.Final:compile
[INFO] |  +- io.undertow:undertow-servlet:jar:2.3.18.Final:compile
[INFO] |  +- io.undertow:undertow-websockets-jsr:jar:2.3.18.Final:compile
[INFO] |  |  +- jakarta.websocket:jakarta.websocket-api:jar:2.1.1:compile
[INFO] |  |  \- jakarta.websocket:jakarta.websocket-client-api:jar:2.1.1:compile
[INFO] |  \- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] +- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |     +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |     |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |     +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |     |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |     \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  |  \- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] --------------------< com.pig4cloud:pig-common-bom >--------------------
[INFO] Building pig-common-bom 3.8.3                                    [18/24]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-bom ---
[INFO] com.pig4cloud:pig-common-bom:pom:3.8.3
[INFO] 
[INFO] ----------------< com.pig4cloud:pig-common-datasource >-----------------
[INFO] Building pig-common-datasource 3.8.3                             [19/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-datasource ---
[INFO] com.pig4cloud:pig-common-datasource:jar:3.8.3
[INFO] +- com.baomidou:dynamic-datasource-spring-boot3-starter:jar:4.3.1:compile
[INFO] |  \- com.baomidou:dynamic-datasource-spring-boot-common:jar:4.3.1:compile
[INFO] |     +- com.baomidou:dynamic-datasource-spring:jar:4.3.1:compile
[INFO] |     |  +- com.baomidou:dynamic-datasource-creator:jar:4.3.1:compile
[INFO] |     |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |     |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |     |  +- org.springframework:spring-jdbc:jar:6.2.5:compile
[INFO] |     |  |  \- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |     |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |     |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |     \- org.springframework.boot:spring-boot-starter-aop:jar:3.4.4:compile
[INFO] |        \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:runtime
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:runtime
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:runtime
[INFO] |  |  \- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:runtime
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] |     \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] -------------------< com.pig4cloud:pig-common-seata >-------------------
[INFO] Building pig-common-seata 3.8.3                                  [20/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-common-seata ---
[INFO] com.pig4cloud:pig-common-seata:jar:3.8.3
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |     \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |     +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |     \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  +- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |     +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  |  |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  +- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |        +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |           \- com.google.protobuf:protobuf-java:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:runtime
[INFO] |  |  |  |  |  |     +- com.squareup.okio:okio:jar:3.6.0:runtime
[INFO] |  |  |  |  |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:runtime
[INFO] |  |  |  |  |  |        |  \- org.jetbrains:annotations:jar:13.0:runtime
[INFO] |  |  |  |  |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.14.0:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-seata:jar:2023.0.3.2:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-aop:jar:3.4.4:compile
[INFO] |  |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] |  \- org.apache.seata:seata-spring-boot-starter:jar:2.1.0:compile
[INFO] |     +- org.apache.seata:seata-spring-autoconfigure-client:jar:2.1.0:compile
[INFO] |     |  \- org.apache.seata:seata-spring-autoconfigure-core:jar:2.1.0:compile
[INFO] |     \- org.apache.seata:seata-all:jar:2.1.0:compile
[INFO] |        +- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] |        +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |        |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |        |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |        |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |        |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |        |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |        |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |        |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |        +- org.antlr:antlr4:jar:4.8:compile
[INFO] |        |  +- org.antlr:antlr4-runtime:jar:4.8:compile
[INFO] |        |  +- org.antlr:antlr-runtime:jar:3.5.2:compile
[INFO] |        |  +- org.antlr:ST4:jar:4.3:compile
[INFO] |        |  +- org.abego.treelayout:org.abego.treelayout.core:jar:1.0.3:compile
[INFO] |        |  +- org.glassfish:javax.json:jar:1.0.4:compile
[INFO] |        |  \- com.ibm.icu:icu4j:jar:61.1:compile
[INFO] |        +- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |        +- com.alibaba:druid:jar:1.2.7:compile
[INFO] |        |  \- javax.annotation:javax.annotation-api:jar:1.3.2:compile
[INFO] |        +- com.typesafe:config:jar:1.2.1:compile
[INFO] |        +- commons-lang:commons-lang:jar:2.6:compile
[INFO] |        +- org.apache.commons:commons-pool2:jar:2.12.1:compile
[INFO] |        +- commons-pool:commons-pool:jar:1.6:compile
[INFO] |        +- org.apache.dubbo.extensions:dubbo-filter-seata:jar:1.0.2:compile
[INFO] |        +- aopalliance:aopalliance:jar:1.0:compile
[INFO] |        \- com.github.ben-manes.caffeine:caffeine:jar:3.1.8:compile
[INFO] +- com.pig4cloud:pig-common-datasource:jar:3.8.3:compile
[INFO] |  \- com.baomidou:dynamic-datasource-spring-boot3-starter:jar:4.3.1:compile
[INFO] |     \- com.baomidou:dynamic-datasource-spring-boot-common:jar:4.3.1:compile
[INFO] |        \- com.baomidou:dynamic-datasource-spring:jar:4.3.1:compile
[INFO] |           +- com.baomidou:dynamic-datasource-creator:jar:4.3.1:compile
[INFO] |           \- org.springframework:spring-jdbc:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:compile
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] ----------------------< com.pig4cloud:pig-visual >----------------------
[INFO] Building pig-visual 3.8.3                                        [21/24]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-visual ---
[INFO] com.pig4cloud:pig-visual:pom:3.8.3
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  |  \- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  |     +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  |     \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  |  +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |  |  |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |  |  \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  |  +- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] |  |  \- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:runtime
[INFO] |  |  |  +- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:runtime
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:runtime
[INFO] |  |  \- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:runtime
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] |     \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |        +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |        \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] |     \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |        \- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    |  \- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] ---------------------< com.pig4cloud:pig-codegen >----------------------
[INFO] Building pig-codegen 3.8.3                                       [22/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-codegen ---
[INFO] com.pig4cloud:pig-codegen:jar:3.8.3
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.nacos:nacos-client:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-auth-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-encryption-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-logback-adapter-12:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:logback-adapter:jar:1.1.3:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-log4j2-adapter:jar:2.4.2:compile
[INFO] |  |  +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- org.apache.httpcomponents:httpasyncclient:jar:4.1.5:compile
[INFO] |  |  |  +- org.apache.httpcomponents:httpcore-nio:jar:4.4.16:compile
[INFO] |  |  |  \- org.apache.httpcomponents:httpclient:jar:4.5.13:compile
[INFO] |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.16:compile
[INFO] |  |  +- io.prometheus:simpleclient:jar:0.16.0:compile
[INFO] |  |  |  +- io.prometheus:simpleclient_tracer_otel:jar:0.16.0:compile
[INFO] |  |  |  |  \- io.prometheus:simpleclient_tracer_common:jar:0.16.0:compile
[INFO] |  |  |  \- io.prometheus:simpleclient_tracer_otel_agent:jar:0.16.0:compile
[INFO] |  |  +- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  |  \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |  |     +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |  |     \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  \- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] |  \- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] +- com.pig4cloud:pig-common-mybatis:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- com.baomidou:mybatis-plus-spring:jar:3.5.11:compile
[INFO] |  +- com.baomidou:mybatis-plus-jsqlparser:jar:3.5.11:compile
[INFO] |  |  +- com.github.jsqlparser:jsqlparser:jar:5.1:compile
[INFO] |  |  \- com.baomidou:mybatis-plus-jsqlparser-common:jar:3.5.11:compile
[INFO] |  \- io.swagger.core.v3:swagger-annotations-jakarta:jar:2.2.29:compile
[INFO] +- com.pig4cloud:pig-common-datasource:jar:3.8.3:compile
[INFO] |  +- com.baomidou:dynamic-datasource-spring-boot3-starter:jar:4.3.1:compile
[INFO] |  |  \- com.baomidou:dynamic-datasource-spring-boot-common:jar:4.3.1:compile
[INFO] |  |     \- com.baomidou:dynamic-datasource-spring:jar:4.3.1:compile
[INFO] |  |        \- com.baomidou:dynamic-datasource-creator:jar:4.3.1:compile
[INFO] |  \- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] +- com.baomidou:mybatis-plus-spring-boot3-starter:jar:3.5.11:compile
[INFO] |  +- com.baomidou:mybatis-plus:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- org.mybatis:mybatis-spring:jar:3.0.4:compile
[INFO] |  +- com.baomidou:mybatis-plus-spring-boot-autoconfigure:jar:3.5.11:compile
[INFO] |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-jdbc:jar:3.4.4:compile
[INFO] |     +- com.zaxxer:HikariCP:jar:5.1.0:compile
[INFO] |     \- org.springframework:spring-jdbc:jar:6.2.5:compile
[INFO] |        \- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] +- com.mysql:mysql-connector-j:jar:9.2.0:compile
[INFO] |  \- com.google.protobuf:protobuf-java:jar:4.29.0:compile
[INFO] +- org.anyline:anyline-environment-spring-data-jdbc:jar:8.7.2-jdk17-20240808:compile
[INFO] |  +- org.anyline:anyline-data-jdbc:jar:8.7.2-jdk17-20240808:compile
[INFO] |  |  \- org.anyline:anyline-data:jar:8.7.2-jdk17-20240808:compile
[INFO] |  \- org.anyline:anyline-environment-spring:jar:8.7.2-jdk17-20240808:compile
[INFO] |     \- org.anyline:anyline-core:jar:8.7.2-jdk17-20240808:compile
[INFO] |        +- org.dom4j:dom4j:jar:2.1.3:compile
[INFO] |        +- org.anyline:anyline-oro:jar:8.7.2-jdk17-20240808:compile
[INFO] |        \- ognl:ognl:jar:3.2.10:compile
[INFO] |           \- org.javassist:javassist:jar:3.24.1-GA:compile
[INFO] +- org.anyline:anyline-data-jdbc-mysql:jar:8.7.2-jdk17-20240808:compile
[INFO] +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |     \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |     +- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |     \- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |        |  \- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |        |  \- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  +- com.squareup.okio:okio-jvm:jar:3.4.0:compile
[INFO] |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  +- org.checkerframework:checker-qual:jar:3.12.0:compile
[INFO] |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  +- com.google.errorprone:error_prone_annotations:jar:2.14.0:compile
[INFO] |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] +- cn.hutool:hutool-json:jar:5.8.36:compile
[INFO] +- com.pig4cloud:pig-common-swagger:jar:3.8.3:compile
[INFO] |  \- org.springdoc:springdoc-openapi-starter-webmvc-api:jar:2.8.6:compile
[INFO] |     \- org.springdoc:springdoc-openapi-starter-common:jar:2.8.6:compile
[INFO] |        \- io.swagger.core.v3:swagger-core-jakarta:jar:2.2.29:compile
[INFO] |           +- io.swagger.core.v3:swagger-models-jakarta:jar:2.2.29:compile
[INFO] |           \- com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:jar:2.18.3:compile
[INFO] +- com.pig4cloud:pig-common-xss:jar:3.8.3:compile
[INFO] |  +- org.jsoup:jsoup:jar:1.18.3:compile
[INFO] |  +- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] |  |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  \- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |     \- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] +- com.pig4cloud:pig-common-security:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-extra:jar:5.8.36:compile
[INFO] |  |  \- cn.hutool:hutool-setting:jar:5.8.36:compile
[INFO] |  |     \- cn.hutool:hutool-log:jar:5.8.36:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-openfeign:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-starter:jar:4.2.1:compile
[INFO] |  |  |  \- org.bouncycastle:bcprov-jdk18on:jar:1.78.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-openfeign-core:jar:4.2.1:compile
[INFO] |  |  |  \- io.github.openfeign:feign-form-spring:jar:13.5:compile
[INFO] |  |  |     +- io.github.openfeign:feign-form:jar:13.5:compile
[INFO] |  |  |     \- commons-fileupload:commons-fileupload:jar:1.5:compile
[INFO] |  |  +- io.github.openfeign:feign-core:jar:13.5:compile
[INFO] |  |  \- io.github.openfeign:feign-slf4j:jar:13.5:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-jose:jar:6.4.4:compile
[INFO] |  |  \- com.nimbusds:nimbus-jose-jwt:jar:9.37.3:compile
[INFO] |  |     \- com.github.stephenc.jcip:jcip-annotations:jar:1.0-1:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-authorization-server:jar:1.4.2:compile
[INFO] |  |  +- org.springframework.security:spring-security-config:jar:6.4.4:compile
[INFO] |  |  +- org.springframework.security:spring-security-web:jar:6.4.4:compile
[INFO] |  |  \- org.springframework.security:spring-security-oauth2-resource-server:jar:6.4.4:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-aop:jar:3.4.4:compile
[INFO] |     \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] +- com.pig4cloud:pig-common-log:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-http:jar:5.8.36:compile
[INFO] |  +- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-core:jar:6.4.4:compile
[INFO] |  \- com.pig4cloud:pig-common-feign:jar:3.8.3:compile
[INFO] |     +- com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2023.0.3.2:compile
[INFO] |     |  +- com.alibaba.csp:sentinel-transport-simple-http:jar:1.8.8:compile
[INFO] |     |  |  \- com.alibaba.csp:sentinel-transport-common:jar:1.8.8:compile
[INFO] |     |  |     \- com.alibaba.csp:sentinel-datasource-extension:jar:1.8.8:compile
[INFO] |     |  +- com.alibaba.csp:sentinel-annotation-aspectj:jar:1.8.8:compile
[INFO] |     |  |  \- com.alibaba.csp:sentinel-core:jar:1.8.8:compile
[INFO] |     |  +- com.alibaba.cloud:spring-cloud-circuitbreaker-sentinel:jar:2023.0.3.2:compile
[INFO] |     |  |  \- com.alibaba.csp:sentinel-reactor-adapter:jar:1.8.8:compile
[INFO] |     |  +- com.alibaba.csp:sentinel-spring-webflux-adapter:jar:1.8.8:compile
[INFO] |     |  +- com.alibaba.csp:sentinel-spring-webmvc-v6x-adapter:jar:1.8.8:compile
[INFO] |     |  |  \- com.alibaba.csp:sentinel-web-adapter-common:jar:1.8.8:compile
[INFO] |     |  +- com.alibaba.csp:sentinel-parameter-flow-control:jar:1.8.8:compile
[INFO] |     |  |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |     |  +- com.alibaba.csp:sentinel-cluster-server-default:jar:1.8.8:compile
[INFO] |     |  |  \- com.alibaba.csp:sentinel-cluster-common-default:jar:1.8.8:compile
[INFO] |     |  +- com.alibaba.csp:sentinel-cluster-client-default:jar:1.8.8:compile
[INFO] |     |  \- com.alibaba.cloud:spring-cloud-alibaba-sentinel-datasource:jar:2023.0.3.2:compile
[INFO] |     +- io.github.openfeign:feign-okhttp:jar:13.5:compile
[INFO] |     |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:compile
[INFO] |     |     +- com.squareup.okio:okio:jar:3.6.0:compile
[INFO] |     |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:compile
[INFO] |     |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:compile
[INFO] |     |        |  \- org.jetbrains:annotations:jar:13.0:compile
[INFO] |     |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:compile
[INFO] |     +- org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:4.2.1:compile
[INFO] |     |  +- org.springframework.cloud:spring-cloud-loadbalancer:jar:4.2.1:compile
[INFO] |     |  |  \- io.projectreactor.addons:reactor-extra:jar:3.5.2:compile
[INFO] |     |  +- org.springframework.boot:spring-boot-starter-cache:jar:3.4.4:compile
[INFO] |     |  \- com.stoyanr:evictor:jar:1.0.0:compile
[INFO] |     \- com.github.ben-manes.caffeine:caffeine:jar:3.1.8:compile
[INFO] +- org.apache.velocity:velocity-engine-core:jar:2.4:compile
[INFO] |  \- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] +- org.apache.velocity.tools:velocity-tools-generic:jar:3.1:compile
[INFO] |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  +- org.apache.commons:commons-digester3:jar:3.2:compile
[INFO] |  \- com.github.cliftonlabs:json-simple:jar:3.0.2:compile
[INFO] +- group.springframework.plugin:screw-spring-boot-starter:jar:0.0.6:compile
[INFO] |  +- group.springframework.plugin:screw-core:jar:0.0.6:compile
[INFO] |  |  +- org.freemarker:freemarker:jar:2.3.34:compile
[INFO] |  |  +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |  |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |  \- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |     \- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |        +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |        |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |        \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:3.4.4:compile
[INFO] |  \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-starter-undertow:jar:3.4.4:compile
[INFO] |  +- io.undertow:undertow-core:jar:2.3.18.Final:compile
[INFO] |  |  +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |  +- org.jboss.xnio:xnio-api:jar:3.8.16.Final:compile
[INFO] |  |  |  +- org.wildfly.common:wildfly-common:jar:1.5.4.Final:compile
[INFO] |  |  |  \- org.wildfly.client:wildfly-client-config:jar:1.0.1.Final:compile
[INFO] |  |  +- org.jboss.xnio:xnio-nio:jar:3.8.16.Final:runtime
[INFO] |  |  \- org.jboss.threads:jboss-threads:jar:3.5.0.Final:compile
[INFO] |  +- io.undertow:undertow-servlet:jar:2.3.18.Final:compile
[INFO] |  +- io.undertow:undertow-websockets-jsr:jar:2.3.18.Final:compile
[INFO] |  |  +- jakarta.websocket:jakarta.websocket-api:jar:2.1.1:compile
[INFO] |  |  \- jakarta.websocket:jakarta.websocket-client-api:jar:2.1.1:compile
[INFO] |  \- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] +- com.pig4cloud:pig-common-excel:jar:3.8.3:compile
[INFO] |  \- com.pig4cloud.excel:excel-spring-boot-starter:jar:3.4.0:compile
[INFO] |     +- cn.idev.excel:fastexcel:jar:1.0.0:compile
[INFO] |     |  \- cn.idev.excel:fastexcel-core:jar:1.0.0:compile
[INFO] |     |     +- org.apache.commons:commons-csv:jar:1.11.0:compile
[INFO] |     |     +- org.apache.poi:poi:jar:5.2.5:compile
[INFO] |     |     |  +- org.apache.commons:commons-collections4:jar:4.4:compile
[INFO] |     |     |  +- org.apache.commons:commons-math3:jar:3.6.1:compile
[INFO] |     |     |  \- com.zaxxer:SparseBitSet:jar:1.3:compile
[INFO] |     |     +- org.apache.poi:poi-ooxml:jar:5.2.5:compile
[INFO] |     |     |  +- org.apache.poi:poi-ooxml-lite:jar:5.2.5:compile
[INFO] |     |     |  +- org.apache.xmlbeans:xmlbeans:jar:5.2.0:compile
[INFO] |     |     |  \- com.github.virtuald:curvesapi:jar:1.08:compile
[INFO] |     |     +- org.ehcache:ehcache:jar:3.10.8:compile
[INFO] |     |     |  +- javax.cache:cache-api:jar:1.1.1:compile
[INFO] |     |     |  \- org.glassfish.jaxb:jaxb-runtime:jar:4.0.5:runtime
[INFO] |     |     |     \- org.glassfish.jaxb:jaxb-core:jar:4.0.5:runtime
[INFO] |     |     |        +- org.glassfish.jaxb:txw2:jar:4.0.5:runtime
[INFO] |     |     |        \- com.sun.istack:istack-commons-runtime:jar:4.1.2:runtime
[INFO] |     |     +- cn.idev.excel:fastexcel-support:jar:1.0.0:compile
[INFO] |     |     +- com.itextpdf:itext7-core:pom:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:barcodes:jar:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:hyph:jar:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:sign:jar:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:styled-xml-parser:jar:7.1.15:compile
[INFO] |     |     |  \- com.itextpdf:svg:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:kernel:jar:7.1.15:compile
[INFO] |     |     |  +- org.bouncycastle:bcpkix-jdk15on:jar:1.68:compile
[INFO] |     |     |  \- org.bouncycastle:bcprov-jdk15on:jar:1.68:compile
[INFO] |     |     +- com.itextpdf:io:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:layout:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:forms:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:pdfa:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:pdftest:jar:7.1.15:compile
[INFO] |     |     |  +- junit:junit:jar:4.13.2:compile
[INFO] |     |     |  |  \- org.hamcrest:hamcrest-core:jar:2.2:compile
[INFO] |     |     |  \- org.verapdf:validation-model:jar:1.16.1:compile
[INFO] |     |     |     +- org.verapdf:parser:jar:1.16.1:compile
[INFO] |     |     |     +- org.verapdf:pdf-model:jar:1.16.1:compile
[INFO] |     |     |     +- org.verapdf:core:jar:1.16.1:compile
[INFO] |     |     |     |  +- org.verapdf:verapdf-xmp-core:jar:1.16.1:compile
[INFO] |     |     |     |  +- org.mozilla:rhino:jar:1.7.11:compile
[INFO] |     |     |     |  +- javax.xml.bind:jaxb-api:jar:2.4.0-b180830.0359:compile
[INFO] |     |     |     |  |  \- javax.activation:javax.activation-api:jar:1.2.0:compile
[INFO] |     |     |     |  \- net.java.dev.stax-utils:stax-utils:jar:20070216:compile
[INFO] |     |     |     +- org.verapdf:feature-reporting:jar:1.16.1:compile
[INFO] |     |     |     \- org.verapdf:metadata-fixer:jar:1.16.1:compile
[INFO] |     |     \- com.itextpdf:font-asian:jar:7.1.15:compile
[INFO] |     \- org.apache.commons:commons-compress:jar:1.27.1:compile
[INFO] |        \- commons-io:commons-io:jar:2.18.0:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] ---------------------< com.pig4cloud:pig-monitor >----------------------
[INFO] Building pig-monitor 3.8.3                                       [23/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-monitor ---
[INFO] com.pig4cloud:pig-monitor:jar:3.8.3
[INFO] +- de.codecentric:spring-boot-admin-starter-server:jar:3.4.5:compile
[INFO] |  +- de.codecentric:spring-boot-admin-server:jar:3.4.5:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-webflux:jar:3.4.4:compile
[INFO] |  |  |  +- org.springframework.boot:spring-boot-starter-reactor-netty:jar:3.4.4:compile
[INFO] |  |  |  |  \- io.projectreactor.netty:reactor-netty-http:jar:1.2.4:compile
[INFO] |  |  |  |     +- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  |  |     |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  |     |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  |     |  +- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  |     |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  |     |  \- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |  |     +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  |     +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |     |  +- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  |  |     |  \- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |     +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:compile
[INFO] |  |  |  |     |  \- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |     +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:compile
[INFO] |  |  |  |     |  +- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  |  |  |     |  \- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |     \- io.projectreactor.netty:reactor-netty-core:jar:1.2.4:compile
[INFO] |  |  |  |        \- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |           \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  |  \- org.springframework:spring-webflux:jar:6.2.5:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-thymeleaf:jar:3.4.4:compile
[INFO] |  |  |  \- org.thymeleaf:thymeleaf-spring6:jar:3.1.3.RELEASE:compile
[INFO] |  |  |     \- org.thymeleaf:thymeleaf:jar:3.1.3.RELEASE:compile
[INFO] |  |  |        +- org.attoparser:attoparser:jar:2.0.7.RELEASE:compile
[INFO] |  |  |        \- org.unbescape:unbescape:jar:1.1.6.RELEASE:compile
[INFO] |  |  +- org.apache.httpcomponents.client5:httpclient5:jar:5.4.2:compile
[INFO] |  |  |  +- org.apache.httpcomponents.core5:httpcore5:jar:5.3.3:compile
[INFO] |  |  |  \- org.apache.httpcomponents.core5:httpcore5-h2:jar:5.3.3:compile
[INFO] |  |  \- io.projectreactor.addons:reactor-extra:jar:3.5.2:compile
[INFO] |  |     \- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |        \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  +- de.codecentric:spring-boot-admin-server-ui:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-server-cloud:jar:3.4.5:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.nacos:nacos-client:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-auth-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-encryption-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-logback-adapter-12:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:logback-adapter:jar:1.1.3:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-log4j2-adapter:jar:2.4.2:compile
[INFO] |  |  +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  +- org.apache.httpcomponents:httpasyncclient:jar:4.1.5:compile
[INFO] |  |  |  +- org.apache.httpcomponents:httpcore-nio:jar:4.4.16:compile
[INFO] |  |  |  \- org.apache.httpcomponents:httpclient:jar:4.5.13:compile
[INFO] |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.16:compile
[INFO] |  |  +- io.prometheus:simpleclient:jar:0.16.0:compile
[INFO] |  |  |  +- io.prometheus:simpleclient_tracer_otel:jar:0.16.0:compile
[INFO] |  |  |  |  \- io.prometheus:simpleclient_tracer_common:jar:0.16.0:compile
[INFO] |  |  |  \- io.prometheus:simpleclient_tracer_otel_agent:jar:0.16.0:compile
[INFO] |  |  +- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  |  \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |  |     +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |  |     \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  \- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] |  \- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] +- org.springframework.boot:spring-boot-starter-undertow:jar:3.4.4:compile
[INFO] |  +- io.undertow:undertow-core:jar:2.3.18.Final:compile
[INFO] |  |  +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |  +- org.jboss.xnio:xnio-api:jar:3.8.16.Final:compile
[INFO] |  |  |  +- org.wildfly.common:wildfly-common:jar:1.5.4.Final:compile
[INFO] |  |  |  \- org.wildfly.client:wildfly-client-config:jar:1.0.1.Final:compile
[INFO] |  |  +- org.jboss.xnio:xnio-nio:jar:3.8.16.Final:runtime
[INFO] |  |  \- org.jboss.threads:jboss-threads:jar:3.5.0.Final:compile
[INFO] |  +- io.undertow:undertow-servlet:jar:2.3.18.Final:compile
[INFO] |  |  \- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  +- io.undertow:undertow-websockets-jsr:jar:2.3.18.Final:compile
[INFO] |  |  +- jakarta.websocket:jakarta.websocket-api:jar:2.1.1:compile
[INFO] |  |  \- jakarta.websocket:jakarta.websocket-client-api:jar:2.1.1:compile
[INFO] |  \- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |     +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |     |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |     +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |     |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |     \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  +- org.springframework:spring-web:jar:6.2.5:compile
[INFO] |  \- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] |     \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-starter-security:jar:3.4.4:compile
[INFO] |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  \- org.springframework.security:spring-security-web:jar:6.4.4:compile
[INFO] +- org.springframework.security:spring-security-config:jar:6.4.4:compile
[INFO] |  +- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  \- org.springframework:spring-core:jar:6.2.5:compile
[INFO] |     \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:test
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:test
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] 
[INFO] ----------------------< com.pig4cloud:pig-quartz >----------------------
[INFO] Building pig-quartz 3.8.3                                        [24/24]
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-dependency-plugin:2.8:tree (default-cli) @ pig-quartz ---
[INFO] com.pig4cloud:pig-quartz:jar:3.8.3
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-alibaba-commons:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.nacos:nacos-client:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-auth-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-encryption-plugin:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-logback-adapter-12:jar:2.4.2:compile
[INFO] |  |  +- com.alibaba.nacos:logback-adapter:jar:1.1.3:compile
[INFO] |  |  +- com.alibaba.nacos:nacos-log4j2-adapter:jar:2.4.2:compile
[INFO] |  |  +- commons-codec:commons-codec:jar:1.17.2:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-core:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.core:jackson-databind:jar:2.18.3:compile
[INFO] |  |  |  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.18.3:compile
[INFO] |  |  +- org.apache.httpcomponents:httpasyncclient:jar:4.1.5:compile
[INFO] |  |  |  +- org.apache.httpcomponents:httpcore-nio:jar:4.4.16:compile
[INFO] |  |  |  \- org.apache.httpcomponents:httpclient:jar:4.5.13:compile
[INFO] |  |  +- org.apache.httpcomponents:httpcore:jar:4.4.16:compile
[INFO] |  |  +- io.prometheus:simpleclient:jar:0.16.0:compile
[INFO] |  |  |  +- io.prometheus:simpleclient_tracer_otel:jar:0.16.0:compile
[INFO] |  |  |  |  \- io.prometheus:simpleclient_tracer_common:jar:0.16.0:compile
[INFO] |  |  |  \- io.prometheus:simpleclient_tracer_otel_agent:jar:0.16.0:compile
[INFO] |  |  +- org.yaml:snakeyaml:jar:2.3:compile
[INFO] |  |  \- io.micrometer:micrometer-core:jar:1.14.5:compile
[INFO] |  |     +- org.hdrhistogram:HdrHistogram:jar:2.2.2:runtime
[INFO] |  |     \- org.latencyutils:LatencyUtils:jar:2.0.3:runtime
[INFO] |  +- org.springframework.cloud:spring-cloud-commons:jar:4.2.1:compile
[INFO] |  |  \- org.springframework.security:spring-security-crypto:jar:6.4.4:compile
[INFO] |  \- org.springframework.cloud:spring-cloud-context:jar:4.2.1:compile
[INFO] +- com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- com.alibaba.cloud:spring-alibaba-nacos-config:jar:2023.0.3.2:compile
[INFO] |  +- org.slf4j:slf4j-api:jar:2.0.17:compile
[INFO] |  \- jakarta.annotation:jakarta.annotation-api:jar:2.1.1:compile
[INFO] +- com.pig4cloud:pig-common-log:jar:3.8.3:compile
[INFO] |  +- com.pig4cloud:pig-common-core:jar:3.8.3:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-data-redis:jar:3.4.4:compile
[INFO] |  |  |  +- io.lettuce:lettuce-core:jar:6.4.2.RELEASE:compile
[INFO] |  |  |  |  +- io.netty:netty-common:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-transport:jar:4.1.119.Final:compile
[INFO] |  |  |  \- org.springframework.data:spring-data-redis:jar:3.4.4:compile
[INFO] |  |  |     +- org.springframework.data:spring-data-keyvalue:jar:3.4.4:compile
[INFO] |  |  |     |  \- org.springframework.data:spring-data-commons:jar:3.4.4:compile
[INFO] |  |  |     \- org.springframework:spring-oxm:jar:6.2.5:compile
[INFO] |  |  +- jakarta.servlet:jakarta.servlet-api:jar:6.0.0:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-validation:jar:3.4.4:compile
[INFO] |  |  |  \- org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:compile
[INFO] |  |  |     +- jakarta.validation:jakarta.validation-api:jar:3.0.2:compile
[INFO] |  |  |     \- com.fasterxml:classmate:jar:1.7.0:compile
[INFO] |  |  +- org.springframework.integration:spring-integration-mqtt:jar:6.4.3:compile
[INFO] |  |  |  +- org.springframework.integration:spring-integration-core:jar:6.4.3:compile
[INFO] |  |  |  |  +- org.springframework:spring-messaging:jar:6.2.5:compile
[INFO] |  |  |  |  \- org.springframework.retry:spring-retry:jar:2.0.11:compile
[INFO] |  |  |  \- org.eclipse.paho:org.eclipse.paho.client.mqttv3:jar:1.2.5:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-spring-boot-starter:jar:2.3.4:compile
[INFO] |  |  |  \- org.apache.rocketmq:rocketmq-spring-boot:jar:2.3.4:compile
[INFO] |  |  |     \- org.apache.rocketmq:rocketmq-acl:jar:5.3.1:compile
[INFO] |  |  |        +- org.apache.rocketmq:rocketmq-proto:jar:2.0.3:compile
[INFO] |  |  |        +- org.apache.rocketmq:rocketmq-srvutil:jar:5.3.1:compile
[INFO] |  |  |        |  \- commons-cli:commons-cli:jar:1.5.0:compile
[INFO] |  |  |        +- commons-validator:commons-validator:jar:1.7:compile
[INFO] |  |  |        |  +- commons-beanutils:commons-beanutils:jar:1.9.4:compile
[INFO] |  |  |        |  +- commons-digester:commons-digester:jar:2.1:compile
[INFO] |  |  |        |  +- commons-logging:commons-logging:jar:1.2:compile
[INFO] |  |  |        |  \- commons-collections:commons-collections:jar:3.2.2:compile
[INFO] |  |  |        \- com.google.protobuf:protobuf-java-util:jar:3.20.1:compile
[INFO] |  |  +- org.apache.rocketmq:rocketmq-client:jar:5.3.1:compile
[INFO] |  |  |  +- org.apache.rocketmq:rocketmq-remoting:jar:5.3.1:compile
[INFO] |  |  |  |  +- org.apache.rocketmq:rocketmq-common:jar:5.3.1:compile
[INFO] |  |  |  |  |  +- com.alibaba.fastjson2:fastjson2:jar:2.0.43:compile
[INFO] |  |  |  |  |  +- io.netty:netty-all:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-haproxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-memcache:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-mqtt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-redis:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-smtp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-stomp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-codec-xml:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-handler-ssl-ocsp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-resolver-dns:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-rxtx:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-sctp:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-udt:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-classes-epoll:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-classes-kqueue:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-resolver-dns-classes-macos:jar:4.1.119.Final:compile
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-epoll:jar:linux-riscv64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-transport-native-kqueue:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  +- io.netty:netty-resolver-dns-native-macos:jar:osx-x86_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  |  \- io.netty:netty-resolver-dns-native-macos:jar:osx-aarch_64:4.1.119.Final:runtime
[INFO] |  |  |  |  |  +- com.github.luben:zstd-jni:jar:1.5.2-2:compile
[INFO] |  |  |  |  |  +- org.lz4:lz4-java:jar:1.8.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-trace:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-api-incubator:jar:1.43.0-alpha:runtime
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-metrics:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk-logs:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-otlp-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-exporter-common:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-sender-okhttp:jar:1.43.0:runtime
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-extension-autoconfigure-spi:jar:1.43.0:runtime
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-prometheus:jar:1.29.0-alpha:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-sdk:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  +- io.opentelemetry:opentelemetry-api:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-context:jar:1.43.0:compile
[INFO] |  |  |  |  |  |  \- io.opentelemetry:opentelemetry-sdk-common:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.opentelemetry:opentelemetry-exporter-logging-otlp:jar:1.43.0:compile
[INFO] |  |  |  |  |  +- io.grpc:grpc-stub:jar:1.53.0:compile
[INFO] |  |  |  |  |  +- io.grpc:grpc-netty-shaded:jar:1.53.0:compile
[INFO] |  |  |  |  |  +- org.apache.tomcat:annotations-api:jar:6.0.53:compile
[INFO] |  |  |  |  |  \- org.apache.rocketmq:rocketmq-rocksdb:jar:1.0.2:compile
[INFO] |  |  |  |  \- org.reflections:reflections:jar:0.9.11:compile
[INFO] |  |  |  |     \- org.javassist:javassist:jar:3.21.0-GA:compile
[INFO] |  |  |  +- org.apache.commons:commons-lang3:jar:3.17.0:compile
[INFO] |  |  |  +- io.github.aliyunmq:rocketmq-slf4j-api:jar:1.0.1:compile
[INFO] |  |  |  \- io.github.aliyunmq:rocketmq-logback-classic:jar:1.0.1:compile
[INFO] |  |  +- io.grpc:grpc-netty:jar:1.53.0:compile
[INFO] |  |  |  +- io.netty:netty-codec-http2:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-buffer:jar:4.1.119.Final:compile
[INFO] |  |  |  |  +- io.netty:netty-codec:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-codec-http:jar:4.1.119.Final:compile
[INFO] |  |  |  +- io.netty:netty-handler-proxy:jar:4.1.119.Final:compile
[INFO] |  |  |  |  \- io.netty:netty-codec-socks:jar:4.1.119.Final:compile
[INFO] |  |  |  +- com.google.guava:guava:jar:31.1-android:compile
[INFO] |  |  |  |  +- com.google.guava:failureaccess:jar:1.0.1:compile
[INFO] |  |  |  |  +- com.google.guava:listenablefuture:jar:9999.0-empty-to-avoid-conflict-with-guava:compile
[INFO] |  |  |  |  +- com.google.code.findbugs:jsr305:jar:3.0.2:compile
[INFO] |  |  |  |  \- com.google.j2objc:j2objc-annotations:jar:1.3:compile
[INFO] |  |  |  +- io.perfmark:perfmark-api:jar:0.25.0:runtime
[INFO] |  |  |  \- io.netty:netty-transport-native-unix-common:jar:4.1.119.Final:compile
[INFO] |  |  +- io.grpc:grpc-core:jar:1.53.0:compile
[INFO] |  |  |  +- io.grpc:grpc-api:jar:1.53.0:compile (version selected from constraint [1.53.0,1.53.0])
[INFO] |  |  |  |  \- io.grpc:grpc-context:jar:1.53.0:compile
[INFO] |  |  |  +- com.google.code.gson:gson:jar:2.11.0:runtime
[INFO] |  |  |  +- com.google.android:annotations:jar:4.1.1.4:runtime
[INFO] |  |  |  \- org.codehaus.mojo:animal-sniffer-annotations:jar:1.21:runtime
[INFO] |  |  +- com.baomidou:mybatis-plus-core:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-extension:jar:3.5.11:compile
[INFO] |  |  +- com.googlecode.libphonenumber:libphonenumber:jar:8.13.50:compile
[INFO] |  |  \- org.mapstruct:mapstruct:jar:1.6.3:compile
[INFO] |  +- cn.hutool:hutool-extra:jar:5.8.36:compile
[INFO] |  |  \- cn.hutool:hutool-setting:jar:5.8.36:compile
[INFO] |  |     \- cn.hutool:hutool-log:jar:5.8.36:compile
[INFO] |  +- cn.hutool:hutool-http:jar:5.8.36:compile
[INFO] |  +- org.springframework.security:spring-security-core:jar:6.4.4:compile
[INFO] |  |  +- org.springframework:spring-aop:jar:6.2.5:compile
[INFO] |  |  +- org.springframework:spring-beans:jar:6.2.5:compile
[INFO] |  |  +- org.springframework:spring-context:jar:6.2.5:compile
[INFO] |  |  \- org.springframework:spring-expression:jar:6.2.5:compile
[INFO] |  \- org.springframework.security:spring-security-oauth2-core:jar:6.4.4:compile
[INFO] +- com.pig4cloud:pig-common-feign:jar:3.8.3:compile
[INFO] |  +- com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-transport-simple-http:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-transport-common:jar:1.8.8:compile
[INFO] |  |  |     +- com.alibaba.csp:sentinel-datasource-extension:jar:1.8.8:compile
[INFO] |  |  |     \- com.alibaba:fastjson:jar:1.2.83_noneautotype:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-annotation-aspectj:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-core:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.cloud:spring-cloud-circuitbreaker-sentinel:jar:2023.0.3.2:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-reactor-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webflux-adapter:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-spring-webmvc-v6x-adapter:jar:1.8.8:compile
[INFO] |  |  |  \- com.alibaba.csp:sentinel-web-adapter-common:jar:1.8.8:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-parameter-flow-control:jar:1.8.8:compile
[INFO] |  |  |  \- com.googlecode.concurrentlinkedhashmap:concurrentlinkedhashmap-lru:jar:1.4.2:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-server-default:jar:1.8.8:compile
[INFO] |  |  |  +- com.alibaba.csp:sentinel-cluster-common-default:jar:1.8.8:compile
[INFO] |  |  |  \- io.netty:netty-handler:jar:4.1.119.Final:compile
[INFO] |  |  |     \- io.netty:netty-resolver:jar:4.1.119.Final:compile
[INFO] |  |  +- com.alibaba.csp:sentinel-cluster-client-default:jar:1.8.8:compile
[INFO] |  |  \- com.alibaba.cloud:spring-cloud-alibaba-sentinel-datasource:jar:2023.0.3.2:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-openfeign:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-starter:jar:4.2.1:compile
[INFO] |  |  |  \- org.bouncycastle:bcprov-jdk18on:jar:1.78.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-openfeign-core:jar:4.2.1:compile
[INFO] |  |  |  \- io.github.openfeign:feign-form-spring:jar:13.5:compile
[INFO] |  |  |     +- io.github.openfeign:feign-form:jar:13.5:compile
[INFO] |  |  |     \- commons-fileupload:commons-fileupload:jar:1.5:compile
[INFO] |  |  +- io.github.openfeign:feign-core:jar:13.5:compile
[INFO] |  |  \- io.github.openfeign:feign-slf4j:jar:13.5:compile
[INFO] |  +- io.github.openfeign:feign-okhttp:jar:13.5:compile
[INFO] |  |  \- com.squareup.okhttp3:okhttp:jar:4.12.0:compile
[INFO] |  |     +- com.squareup.okio:okio:jar:3.6.0:compile
[INFO] |  |     |  \- com.squareup.okio:okio-jvm:jar:3.6.0:compile
[INFO] |  |     |     \- org.jetbrains.kotlin:kotlin-stdlib-common:jar:1.9.25:compile
[INFO] |  |     \- org.jetbrains.kotlin:kotlin-stdlib-jdk8:jar:1.9.25:compile
[INFO] |  |        +- org.jetbrains.kotlin:kotlin-stdlib:jar:1.9.25:compile
[INFO] |  |        |  \- org.jetbrains:annotations:jar:13.0:compile
[INFO] |  |        \- org.jetbrains.kotlin:kotlin-stdlib-jdk7:jar:1.9.25:compile
[INFO] |  +- org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:4.2.1:compile
[INFO] |  |  +- org.springframework.cloud:spring-cloud-loadbalancer:jar:4.2.1:compile
[INFO] |  |  |  +- io.projectreactor:reactor-core:jar:3.7.4:compile
[INFO] |  |  |  |  \- org.reactivestreams:reactive-streams:jar:1.0.4:compile
[INFO] |  |  |  \- io.projectreactor.addons:reactor-extra:jar:3.5.2:compile
[INFO] |  |  +- org.springframework.boot:spring-boot-starter-cache:jar:3.4.4:compile
[INFO] |  |  \- com.stoyanr:evictor:jar:1.0.0:compile
[INFO] |  +- com.github.ben-manes.caffeine:caffeine:jar:3.1.8:compile
[INFO] |  |  +- org.checkerframework:checker-qual:jar:3.37.0:compile
[INFO] |  |  \- com.google.errorprone:error_prone_annotations:jar:2.21.1:compile
[INFO] |  \- org.springframework:spring-webmvc:jar:6.2.5:compile
[INFO] +- com.pig4cloud:pig-common-mybatis:jar:3.8.3:compile
[INFO] |  +- cn.hutool:hutool-core:jar:5.8.36:compile
[INFO] |  +- com.baomidou:mybatis-plus-spring:jar:3.5.11:compile
[INFO] |  +- com.baomidou:mybatis-plus-jsqlparser:jar:3.5.11:compile
[INFO] |  |  +- com.github.jsqlparser:jsqlparser:jar:5.1:compile
[INFO] |  |  \- com.baomidou:mybatis-plus-jsqlparser-common:jar:3.5.11:compile
[INFO] |  \- io.swagger.core.v3:swagger-annotations-jakarta:jar:2.2.29:compile
[INFO] +- com.baomidou:mybatis-plus-spring-boot3-starter:jar:3.5.11:compile
[INFO] |  +- com.baomidou:mybatis-plus:jar:3.5.11:compile
[INFO] |  |  +- com.baomidou:mybatis-plus-annotation:jar:3.5.11:compile
[INFO] |  |  \- org.mybatis:mybatis:jar:3.5.19:compile
[INFO] |  +- org.mybatis:mybatis-spring:jar:3.0.4:compile
[INFO] |  +- com.baomidou:mybatis-plus-spring-boot-autoconfigure:jar:3.5.11:compile
[INFO] |  +- org.springframework.boot:spring-boot-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot:jar:3.4.4:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-jdbc:jar:3.4.4:compile
[INFO] |     +- com.zaxxer:HikariCP:jar:5.1.0:compile
[INFO] |     \- org.springframework:spring-jdbc:jar:6.2.5:compile
[INFO] +- com.mysql:mysql-connector-j:jar:9.2.0:compile
[INFO] |  \- com.google.protobuf:protobuf-java:jar:4.29.0:compile
[INFO] +- com.pig4cloud:pig-common-swagger:jar:3.8.3:compile
[INFO] |  \- org.springdoc:springdoc-openapi-starter-webmvc-api:jar:2.8.6:compile
[INFO] |     \- org.springdoc:springdoc-openapi-starter-common:jar:2.8.6:compile
[INFO] |        \- io.swagger.core.v3:swagger-core-jakarta:jar:2.2.29:compile
[INFO] |           +- io.swagger.core.v3:swagger-models-jakarta:jar:2.2.29:compile
[INFO] |           \- com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:jar:2.18.3:compile
[INFO] +- com.pig4cloud:pig-common-security:jar:3.8.3:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-jose:jar:6.4.4:compile
[INFO] |  |  \- com.nimbusds:nimbus-jose-jwt:jar:9.37.3:compile
[INFO] |  |     \- com.github.stephenc.jcip:jcip-annotations:jar:1.0-1:compile
[INFO] |  +- org.springframework.security:spring-security-oauth2-authorization-server:jar:1.4.2:compile
[INFO] |  |  +- org.springframework.security:spring-security-config:jar:6.4.4:compile
[INFO] |  |  +- org.springframework.security:spring-security-web:jar:6.4.4:compile
[INFO] |  |  \- org.springframework.security:spring-security-oauth2-resource-server:jar:6.4.4:compile
[INFO] |  \- org.springframework.boot:spring-boot-starter-aop:jar:3.4.4:compile
[INFO] |     \- org.aspectj:aspectjweaver:jar:1.9.23:compile
[INFO] +- org.springframework.boot:spring-boot-starter-quartz:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-starter-logging:jar:3.4.4:compile
[INFO] |  |     +- ch.qos.logback:logback-classic:jar:1.5.18:compile
[INFO] |  |     |  \- ch.qos.logback:logback-core:jar:1.5.18:compile
[INFO] |  |     +- org.apache.logging.log4j:log4j-to-slf4j:jar:2.24.3:compile
[INFO] |  |     |  \- org.apache.logging.log4j:log4j-api:jar:2.24.3:compile
[INFO] |  |     \- org.slf4j:jul-to-slf4j:jar:2.0.17:compile
[INFO] |  +- org.springframework:spring-context-support:jar:6.2.5:compile
[INFO] |  +- org.springframework:spring-tx:jar:6.2.5:compile
[INFO] |  \- org.quartz-scheduler:quartz:jar:2.3.2:compile
[INFO] |     \- com.mchange:mchange-commons-java:jar:0.2.15:compile
[INFO] +- org.springframework.boot:spring-boot-starter-web:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-starter-json:jar:3.4.4:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jdk8:jar:2.18.3:compile
[INFO] |  |  +- com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.18.3:compile
[INFO] |  |  \- com.fasterxml.jackson.module:jackson-module-parameter-names:jar:2.18.3:compile
[INFO] |  \- org.springframework:spring-web:jar:6.2.5:compile
[INFO] +- org.springframework.boot:spring-boot-starter-undertow:jar:3.4.4:compile
[INFO] |  +- io.undertow:undertow-core:jar:2.3.18.Final:compile
[INFO] |  |  +- org.jboss.logging:jboss-logging:jar:3.6.1.Final:compile
[INFO] |  |  +- org.jboss.xnio:xnio-api:jar:3.8.16.Final:compile
[INFO] |  |  |  +- org.wildfly.common:wildfly-common:jar:1.5.4.Final:compile
[INFO] |  |  |  \- org.wildfly.client:wildfly-client-config:jar:1.0.1.Final:compile
[INFO] |  |  +- org.jboss.xnio:xnio-nio:jar:3.8.16.Final:runtime
[INFO] |  |  \- org.jboss.threads:jboss-threads:jar:3.5.0.Final:compile
[INFO] |  +- io.undertow:undertow-servlet:jar:2.3.18.Final:compile
[INFO] |  +- io.undertow:undertow-websockets-jsr:jar:2.3.18.Final:compile
[INFO] |  |  +- jakarta.websocket:jakarta.websocket-api:jar:2.1.1:compile
[INFO] |  |  \- jakarta.websocket:jakarta.websocket-client-api:jar:2.1.1:compile
[INFO] |  \- org.apache.tomcat.embed:tomcat-embed-el:jar:10.1.39:compile
[INFO] +- com.pig4cloud:pig-common-excel:jar:3.8.3:compile
[INFO] |  \- com.pig4cloud.excel:excel-spring-boot-starter:jar:3.4.0:compile
[INFO] |     +- cn.idev.excel:fastexcel:jar:1.0.0:compile
[INFO] |     |  \- cn.idev.excel:fastexcel-core:jar:1.0.0:compile
[INFO] |     |     +- org.apache.commons:commons-csv:jar:1.11.0:compile
[INFO] |     |     +- org.apache.poi:poi:jar:5.2.5:compile
[INFO] |     |     |  +- org.apache.commons:commons-collections4:jar:4.4:compile
[INFO] |     |     |  +- org.apache.commons:commons-math3:jar:3.6.1:compile
[INFO] |     |     |  \- com.zaxxer:SparseBitSet:jar:1.3:compile
[INFO] |     |     +- org.apache.poi:poi-ooxml:jar:5.2.5:compile
[INFO] |     |     |  +- org.apache.poi:poi-ooxml-lite:jar:5.2.5:compile
[INFO] |     |     |  +- org.apache.xmlbeans:xmlbeans:jar:5.2.0:compile
[INFO] |     |     |  \- com.github.virtuald:curvesapi:jar:1.08:compile
[INFO] |     |     +- org.ehcache:ehcache:jar:3.10.8:compile
[INFO] |     |     |  +- javax.cache:cache-api:jar:1.1.1:compile
[INFO] |     |     |  \- org.glassfish.jaxb:jaxb-runtime:jar:4.0.5:runtime
[INFO] |     |     |     \- org.glassfish.jaxb:jaxb-core:jar:4.0.5:runtime
[INFO] |     |     |        +- org.glassfish.jaxb:txw2:jar:4.0.5:runtime
[INFO] |     |     |        \- com.sun.istack:istack-commons-runtime:jar:4.1.2:runtime
[INFO] |     |     +- cn.idev.excel:fastexcel-support:jar:1.0.0:compile
[INFO] |     |     +- com.itextpdf:itext7-core:pom:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:barcodes:jar:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:hyph:jar:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:sign:jar:7.1.15:compile
[INFO] |     |     |  +- com.itextpdf:styled-xml-parser:jar:7.1.15:compile
[INFO] |     |     |  \- com.itextpdf:svg:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:kernel:jar:7.1.15:compile
[INFO] |     |     |  +- org.bouncycastle:bcpkix-jdk15on:jar:1.68:compile
[INFO] |     |     |  \- org.bouncycastle:bcprov-jdk15on:jar:1.68:compile
[INFO] |     |     +- com.itextpdf:io:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:layout:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:forms:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:pdfa:jar:7.1.15:compile
[INFO] |     |     +- com.itextpdf:pdftest:jar:7.1.15:compile
[INFO] |     |     |  +- junit:junit:jar:4.13.2:compile
[INFO] |     |     |  |  \- org.hamcrest:hamcrest-core:jar:2.2:compile
[INFO] |     |     |  \- org.verapdf:validation-model:jar:1.16.1:compile
[INFO] |     |     |     +- org.verapdf:parser:jar:1.16.1:compile
[INFO] |     |     |     +- org.verapdf:pdf-model:jar:1.16.1:compile
[INFO] |     |     |     +- org.verapdf:core:jar:1.16.1:compile
[INFO] |     |     |     |  +- org.verapdf:verapdf-xmp-core:jar:1.16.1:compile
[INFO] |     |     |     |  +- org.mozilla:rhino:jar:1.7.11:compile
[INFO] |     |     |     |  +- javax.xml.bind:jaxb-api:jar:2.4.0-b180830.0359:compile
[INFO] |     |     |     |  |  \- javax.activation:javax.activation-api:jar:1.2.0:compile
[INFO] |     |     |     |  \- net.java.dev.stax-utils:stax-utils:jar:20070216:compile
[INFO] |     |     |     +- org.verapdf:feature-reporting:jar:1.16.1:compile
[INFO] |     |     |     \- org.verapdf:metadata-fixer:jar:1.16.1:compile
[INFO] |     |     \- com.itextpdf:font-asian:jar:7.1.15:compile
[INFO] |     \- org.apache.commons:commons-compress:jar:1.27.1:compile
[INFO] |        \- commons-io:commons-io:jar:2.18.0:compile
[INFO] +- org.springframework.boot:spring-boot-configuration-processor:jar:3.4.4:compile
[INFO] +- com.github.ulisesbocchio:jasypt-spring-boot-starter:jar:3.0.5:compile
[INFO] |  \- com.github.ulisesbocchio:jasypt-spring-boot:jar:3.0.5:compile
[INFO] |     \- org.jasypt:jasypt:jar:1.9.3:compile
[INFO] +- org.springframework.boot:spring-boot-starter-actuator:jar:3.4.4:compile
[INFO] |  +- org.springframework.boot:spring-boot-actuator-autoconfigure:jar:3.4.4:compile
[INFO] |  |  \- org.springframework.boot:spring-boot-actuator:jar:3.4.4:compile
[INFO] |  +- io.micrometer:micrometer-observation:jar:1.14.5:compile
[INFO] |  |  \- io.micrometer:micrometer-commons:jar:1.14.5:compile
[INFO] |  \- io.micrometer:micrometer-jakarta9:jar:1.14.5:compile
[INFO] +- de.codecentric:spring-boot-admin-starter-client:jar:3.4.5:compile
[INFO] |  \- de.codecentric:spring-boot-admin-client:jar:3.4.5:compile
[INFO] +- org.projectlombok:lombok:jar:1.18.36:provided
[INFO] +- com.sun.xml.bind:jaxb-impl:jar:4.0.5:compile
[INFO] |  \- com.sun.xml.bind:jaxb-core:jar:4.0.5:compile
[INFO] |     \- org.eclipse.angus:angus-activation:jar:2.0.2:runtime
[INFO] \- org.springframework.boot:spring-boot-starter-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test:jar:3.4.4:test
[INFO]    +- org.springframework.boot:spring-boot-test-autoconfigure:jar:3.4.4:test
[INFO]    +- com.jayway.jsonpath:json-path:jar:2.9.0:test
[INFO]    +- jakarta.xml.bind:jakarta.xml.bind-api:jar:4.0.2:compile
[INFO]    |  \- jakarta.activation:jakarta.activation-api:jar:2.1.3:compile
[INFO]    +- net.minidev:json-smart:jar:2.5.2:test
[INFO]    |  \- net.minidev:accessors-smart:jar:2.5.2:test
[INFO]    |     \- org.ow2.asm:asm:jar:9.7.1:test
[INFO]    +- org.assertj:assertj-core:jar:3.26.3:test
[INFO]    |  \- net.bytebuddy:byte-buddy:jar:1.15.11:test
[INFO]    +- org.awaitility:awaitility:jar:4.2.2:compile
[INFO]    +- org.hamcrest:hamcrest:jar:2.2:compile
[INFO]    +- org.junit.jupiter:junit-jupiter:jar:5.11.4:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-api:jar:5.11.4:test
[INFO]    |  |  +- org.opentest4j:opentest4j:jar:1.3.0:test
[INFO]    |  |  +- org.junit.platform:junit-platform-commons:jar:1.11.4:test
[INFO]    |  |  \- org.apiguardian:apiguardian-api:jar:1.1.2:test
[INFO]    |  +- org.junit.jupiter:junit-jupiter-params:jar:5.11.4:test
[INFO]    |  \- org.junit.jupiter:junit-jupiter-engine:jar:5.11.4:test
[INFO]    |     \- org.junit.platform:junit-platform-engine:jar:1.11.4:test
[INFO]    +- org.mockito:mockito-core:jar:5.14.2:test
[INFO]    |  +- net.bytebuddy:byte-buddy-agent:jar:1.15.11:test
[INFO]    |  \- org.objenesis:objenesis:jar:3.3:test
[INFO]    +- org.mockito:mockito-junit-jupiter:jar:5.14.2:test
[INFO]    +- org.skyscreamer:jsonassert:jar:1.5.3:test
[INFO]    |  \- com.vaadin.external.google:android-json:jar:0.0.20131108.vaadin1:test
[INFO]    +- org.springframework:spring-core:jar:6.2.5:compile
[INFO]    |  \- org.springframework:spring-jcl:jar:6.2.5:compile
[INFO]    +- org.springframework:spring-test:jar:6.2.5:test
[INFO]    \- org.xmlunit:xmlunit-core:jar:2.10.0:test
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for pig 3.8.3:
[INFO] 
[INFO] pig ................................................ SUCCESS [  0.663 s]
[INFO] pig-register ....................................... SUCCESS [  0.491 s]
[INFO] pig-common ......................................... SUCCESS [  0.042 s]
[INFO] pig-common-core .................................... SUCCESS [  0.336 s]
[INFO] pig-common-security ................................ SUCCESS [  0.171 s]
[INFO] pig-common-feign ................................... SUCCESS [  0.199 s]
[INFO] pig-common-log ..................................... SUCCESS [  0.143 s]
[INFO] pig-common-mybatis ................................. SUCCESS [  0.112 s]
[INFO] pig-common-excel ................................... SUCCESS [  0.189 s]
[INFO] pig-upms ........................................... SUCCESS [  0.031 s]
[INFO] pig-upms-api ....................................... SUCCESS [  0.153 s]
[INFO] pig-gateway ........................................ SUCCESS [  0.233 s]
[INFO] pig-auth ........................................... SUCCESS [  0.202 s]
[INFO] pig-common-oss ..................................... SUCCESS [  0.049 s]
[INFO] pig-common-swagger ................................. SUCCESS [  0.120 s]
[INFO] pig-common-xss ..................................... SUCCESS [  0.102 s]
[INFO] pig-upms-biz ....................................... SUCCESS [  0.227 s]
[INFO] pig-common-bom ..................................... SUCCESS [  0.001 s]
[INFO] pig-common-datasource .............................. SUCCESS [  0.054 s]
[INFO] pig-common-seata ................................... SUCCESS [  0.136 s]
[INFO] pig-visual ......................................... SUCCESS [  0.032 s]
[INFO] pig-codegen ........................................ SUCCESS [  0.218 s]
[INFO] pig-monitor ........................................ SUCCESS [  0.085 s]
[INFO] pig-quartz ......................................... SUCCESS [  0.178 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.585 s
[INFO] Finished at: 2026-06-17T17:46:12+08:00
[INFO] ------------------------------------------------------------------------
