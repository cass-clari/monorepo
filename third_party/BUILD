# Not test_only due to //src/java_tools/junitrunner/java/com/google/testing/junit/junit4:runner
package(default_visibility = ["//visibility:public"])

java_library(
    name = "junit4",
    exports = [
        "@maven//:junit_junit",
        "@maven//:org_hamcrest_hamcrest_core",
    ],
)

java_library(
    name = "testNG",
    exports = [
        "@maven//:org_slf4j_slf4j_simple",
        "@maven//:org_testng_testng",
        "@maven//:org_yaml_snakeyaml",
    ],
)

java_library(
    name = "protobuf",
    exports = [
        "@maven//:com_google_protobuf_protobuf_java",
    ],
)

java_library(
    name = "grpc",
    exports = [
        "@maven//:com_google_android_annotations",
        "@maven//:com_google_api_grpc_proto_google_common_protos",
        "@maven//:com_google_auth_google_auth_library_credentials",
        "@maven//:com_google_auth_google_auth_library_oauth2_http",
        "@maven//:com_google_auto_value_auto_value",
        "@maven//:com_google_auto_value_auto_value_annotations",
        "@maven//:com_google_code_findbugs_jsr305",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_errorprone_error_prone_annotations",
        "@maven//:com_google_guava_failureaccess",
        "@maven//:com_google_guava_guava",
        "@maven//:com_google_re2j_re2j",
        "@maven//:com_google_truth_truth",
        "@maven//:com_squareup_okhttp_okhttp",
        "@maven//:com_squareup_okio_okio",
        "@maven//:io_netty_netty_buffer",
        "@maven//:io_netty_netty_codec",
        "@maven//:io_netty_netty_codec_http",
        "@maven//:io_netty_netty_codec_http2",
        "@maven//:io_netty_netty_codec_socks",
        "@maven//:io_netty_netty_common",
        "@maven//:io_netty_netty_handler",
        "@maven//:io_netty_netty_handler_proxy",
        "@maven//:io_netty_netty_resolver",
        "@maven//:io_netty_netty_tcnative_boringssl_static",
        "@maven//:io_netty_netty_tcnative_classes",
        "@maven//:io_netty_netty_transport",
        #        "@maven//:io_netty_netty_transport_native_epoll_jar_linux_x86_64",
        "@maven//:io_netty_netty_transport_native_unix_common",
        "@maven//:io_opencensus_opencensus_api",
        "@maven//:io_opencensus_opencensus_contrib_grpc_metrics",
        "@maven//:io_perfmark_perfmark_api",
        "@maven//:junit_junit",
        "@maven//:org_apache_tomcat_annotations_api",
        "@maven//:org_codehaus_mojo_animal_sniffer_annotations",
        "@maven//:io_grpc_grpc_services",
    ],
)

java_library(
    name = "spring-test",
    exports = [
        "@maven//:org_springframework_spring_test",
    ],
)

java_library(
    name = "spring-beans-core-context",
    exports = [
        "@maven//:org_springframework_boot_spring_boot",
        "@maven//:org_springframework_boot_spring_boot_autoconfigure",
        "@maven//:org_springframework_boot_spring_boot_starter",
        "@maven//:org_springframework_spring_beans",
        "@maven//:org_springframework_spring_context",
        "@maven//:org_springframework_spring_core",
    ],
)

#java_library(
#    name = "grpc-server-spring-boot-starter",
#    exports = [
#        "@maven//:net_devh_grpc_server_spring_boot_autoconfigure",
#        "@maven//:net_devh_grpc_server_spring_boot_starter",
#        "@maven//:org_springframework_boot_spring_boot",
#        "@maven//:org_springframework_boot_spring_boot_autoconfigure",
#        "@maven//:org_springframework_boot_spring_boot_starter",
#        "@maven//:org_springframework_spring_context",
#    ],
#)
#
#java_library(
#    name = "grpc-client-spring-boot-starter",
#    exports = [
#        "@maven//:net_devh_grpc_client_spring_boot_starter",
#    ],
#)

java_library(
    name = "grpc-testing",
    testonly = True,
    exports = [
        "@maven//:io_grpc_grpc_testing",
    ],
)
