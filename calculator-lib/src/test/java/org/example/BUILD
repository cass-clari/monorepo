#load("@rules_java//java:defs.bzl", "java_test")

java_library(
    name= "TestLib",
    srcs = glob([
        "Test*.java",
    ]),
    deps = [
        "//third_party:testNG",
        "//third_party:junit4",
        "//calculator-lib/src/main/java/org/example:CalculatorLibrary",
    ],
)

java_test(
    name = "TestCalculator",
    size = "small",
    runtime_deps = [
        ":TestLib",
        "//third_party:testNG",
        "//calculator-lib/src/main/java/org/example:CalculatorLibrary",
    ],
    data=["testNG.yaml"],
    use_testrunner=False,
    main_class='org.testng.TestNG',
    args=["calculator-lib/src/test/java/org/example/testNG.yaml"],
)
