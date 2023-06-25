workspace(name = "monorepo")

#maven
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "4.5"
RULES_JVM_EXTERNAL_SHA = "b17d7388feb9bfa7f2fa09031b32707df529f26c91ab9e5d909eb1676badd9a6"

http_archive(
    name = "rules_jvm_external",
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    sha256 = RULES_JVM_EXTERNAL_SHA,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "org.springframework.boot:spring-boot-starter:3.1.1",
        # "com.github.javafaker:javafaker:1.0.2",
        # The following jars are for testing.
        # junit is not test only due to //src/java_tools/junitrunner/java/com/google/testing/junit/junit4:runner,
        # and hamcrest is a dependency of junit.
        "junit:junit:4.13.2",
        "org.hamcrest:hamcrest-core:1.3",
        "org.testng:testng:7.8.0",
        "org.slf4j:slf4j-simple:jar:2.0.7",
    ],
    #fail_if_repin_required = False,
    #maven_install_json = "//:maven_install.json",
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
    #strict_visibility = True,
)