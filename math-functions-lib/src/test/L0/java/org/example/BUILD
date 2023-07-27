load("@rules_java//java:defs.bzl", "java_test")
load("@rules_java//java:defs.bzl", "java_library")

java_test(
    name = "TestAdder",
    size = "small",
    srcs = ["TestAdder.java"],
    tags = [
        "L0",
    ],
    test_class = "test.L0.java.org.example.TestAdder",
    deps = [
        "//third_party:junit4",
        "@monorepo//math-functions-lib/src/main/java/org/example",
    ],
)

java_test(
    name = "TestSubtractor",
    size = "small",
    srcs = ["TestSubtractor.java"],
    tags = [
        "L0",
    ],
    test_class = "test.L0.java.org.example.TestSubtractor",
    deps = [
        "//third_party:junit4",
        "@monorepo//math-functions-lib/src/main/java/org/example",
    ],
)

java_test(
    name = "TestMultiplier",
    size = "small",
    srcs = ["TestMultiplier.java"],
    tags = [
        "L0",
    ],
    test_class = "test.L0.java.org.example.TestMultiplier",
    deps = [
        "//third_party:junit4",
        "@monorepo//math-functions-lib/src/main/java/org/example",
    ],
)

java_test(
    name = "TestDivider",
    size = "small",
    srcs = ["TestDivider.java"],
    tags = [
        "L0",
    ],
    test_class = "test.L0.java.org.example.TestDivider",
    deps = [
        "//third_party:junit4",
        "@monorepo//math-functions-lib/src/main/java/org/example",
    ],
)
