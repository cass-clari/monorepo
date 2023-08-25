load("@rules_java//java:defs.bzl", "java_test")
load("@rules_java//java:defs.bzl", "java_library")

java_library(
    name = "TestLib",
    srcs = glob([
        "Test*.java",
    ]),
    deps = [
        "//calculator-lib/src/main/java/org/example:CalculatorLibrary",
        "//third_party:junit4",
        "//third_party:testNG",
    ],
)

java_test(
    name = "example",
    size = "small",
    args = [
        "-d",
        "test_report",
        "-usedefaultlisteners",
        "true",
        "calculator-lib/src/test/L0/java/org/example/testNG.yaml",
    ],
    data = ["testNG.yaml"],
    main_class = "org.testng.TestNG",
    tags = [
        "L0",
    ],
    use_testrunner = False,
    runtime_deps = [
        ":TestLib",
        "//calculator-lib/src/main/java/org/example:CalculatorLibrary",
        "//third_party:testNG",
    ],
)
