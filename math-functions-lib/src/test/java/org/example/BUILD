load("@rules_java//java:defs.bzl", "java_test")
load("@rules_java//java:defs.bzl", "java_library")

#java_test(
#    name = "L0Tests-test",
#    size = "small",
#    srcs = glob(["*.java"]),
#    test_class = "test.java.org.example.TestSuite",
#    deps = [
#        "//third_party:junit4",
#        "@monorepo//math-functions-lib/src/main/java/org/example",
#    ],
#)

#test_suite(
#    name = "L0Tests",
#    tests = [
#        "L0Tests-test",
#    ],
#)

java_test(
    name = "TestAdder",
    size = "small",
    srcs = ["TestAdder.java"],
    test_class = "test.java.org.example.TestAdder",
    deps = [
        "//third_party:junit4",
        "@monorepo//math-functions-lib/src/main/java/org/example",
    ],
)

java_test(
    name = "TestSubtractor",
    size = "small",
    srcs = ["TestSubtractor.java"],
    test_class = "test.java.org.example.TestSubtractor",
    deps = [
        "//third_party:junit4",
        "@monorepo//math-functions-lib/src/main/java/org/example",
    ],
)

java_test(
    name = "TestMultiplier",
    size = "small",
    srcs = ["TestMultiplier.java"],
    test_class = "test.java.org.example.TestMultiplier",
    deps = [
        "//third_party:junit4",
        "@monorepo//math-functions-lib/src/main/java/org/example",
    ],
)
