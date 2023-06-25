load("@rules_java//java:defs.bzl", "java_test")

java_test(
    name = "TestAdder",
    size = "small",
    srcs = ["TestAdder.java"],
    test_class = "test.java.org.example.TestAdder",
    deps = [
        "//third_party:junit4",
        "@monorepo//math-functions-lib/src/main/java/org/example:Adder",
    ],
)

java_test(
    name = "TestSubtractor",
    size = "small",
    srcs = ["TestSubtractor.java"],
    test_class = "test.java.org.example.TestSubtractor",
    deps = [
        "//third_party:junit4",
        "@monorepo//math-functions-lib/src/main/java/org/example:MathFunctions",
    ],
)
