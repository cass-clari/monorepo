workspace(name = "monorepo")

#maven
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "4.5"

RULES_JVM_EXTERNAL_SHA = "b17d7388feb9bfa7f2fa09031b32707df529f26c91ab9e5d909eb1676badd9a6"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

#http_archive(
#    name = "com_google_absl",
#    strip_prefix = "abseil-cpp-273292d1cfc0a94a65082ee350509af1d113344d",
#    urls = ["https://github.com/abseil/abseil-cpp/archive/273292d1cfc0a94a65082ee350509af1d113344d.zip"],
#)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

load("@rules_jvm_external//:defs.bzl", "maven_install")

http_archive(
    name = "io_grpc_grpc_java",
    sha256 = "17dd91014032a147c978ae99582fddd950f5444388eae700cf51eda0326ad2f9",
    strip_prefix = "grpc-java-1.56.1",
    urls = ["https://github.com/grpc/grpc-java/archive/refs/tags/v1.56.1.tar.gz"],
)

load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS", "grpc_java_repositories")

grpc_java_repositories()

load("@com_google_protobuf//:protobuf_deps.bzl", "PROTOBUF_MAVEN_ARTIFACTS")
load("@com_google_protobuf//:protobuf_deps.bzl", "protobuf_deps")

protobuf_deps()

#load("@io_grpc_grpc_java//:repositories.bzl", "IO_GRPC_GRPC_JAVA_ARTIFACTS", "IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS", "grpc_java_repositories")

maven_install(
    artifacts = [
        "org.springframework.boot:spring-boot-starter:3.1.1",
        # "com.github.javafaker:javafaker:1.0.2",
        # The following jars are for testing.
        # junit is not test only due to //src/java_tools/junitrunner/java/com/google/testing/junit/junit4:runner,
        # and hamcrest is a dependency of junit.
        #"junit:junit:4.13.2",
        "org.hamcrest:hamcrest-core:1.3",
        "org.testng:testng:7.8.0",
        "org.slf4j:slf4j-simple:jar:2.0.7",
        "com.google.protobuf:protobuf-java:3.23.4",
    ] + IO_GRPC_GRPC_JAVA_ARTIFACTS + PROTOBUF_MAVEN_ARTIFACTS,
    #fail_if_repin_required = False,
    #maven_install_json = "//:maven_install.json",
    generate_compat_repositories = True,
    override_targets = IO_GRPC_GRPC_JAVA_OVERRIDE_TARGETS,
    repositories = [
        "https://repo1.maven.org/maven2",
        "https://repo.maven.apache.org/maven2/",
    ],
    #strict_visibility = True,
)

## Protobuffs

http_archive(
    name = "rules_proto_grpc",
    sha256 = "928e4205f701b7798ce32f3d2171c1918b363e9a600390a25c876f075f1efc0a",
    strip_prefix = "rules_proto_grpc-4.4.0",
    urls = ["https://github.com/rules-proto-grpc/rules_proto_grpc/releases/download/4.4.0/rules_proto_grpc-4.4.0.tar.gz"],
)

load("@rules_proto_grpc//:repositories.bzl", "rules_proto_grpc_repos", "rules_proto_grpc_toolchains")

rules_proto_grpc_toolchains()

rules_proto_grpc_repos()

load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")

rules_proto_dependencies()

rules_proto_toolchains()

load("@maven//:compat.bzl", "compat_repositories")

compat_repositories()

grpc_java_repositories()

## protobuff rpc

#load("//:repositories.bzl", "rules_proto_grpc_repos", "rules_proto_grpc_toolchains")

#
# Toolchains
#

#rules_proto_grpc_toolchains()

#
# Core
#

#rules_proto_grpc_repos()

#load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")
#
#rules_proto_dependencies()
#
#rules_proto_toolchains()

## Protobuf RPC

#load("@rules_proto_grpc//:repositories.bzl", "rules_proto_grpc_repos", "rules_proto_grpc_toolchains")
#
#rules_proto_grpc_toolchains()
#
#rules_proto_grpc_repos()
#
#load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")
#
#rules_proto_dependencies()
#
#rules_proto_toolchains()
#
#load("@rules_proto_grpc//java:repositories.bzl", rules_proto_grpc_java_repos = "java_repos")
#
#rules_proto_grpc_java_repos()
#
#load("@rules_jvm_external//:defs.bzl", "maven_install")

#load("@maven//:compat.bzl", "compat_repositories")
#
#compat_repositories()
#
#grpc_java_repositories()
