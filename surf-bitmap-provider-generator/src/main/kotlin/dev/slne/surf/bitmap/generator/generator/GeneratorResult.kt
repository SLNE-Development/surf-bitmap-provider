package dev.slne.surf.bitmap.generator.generator

enum class GeneratorResult(val priority: Int) {
    FILE_GENERATED(2),
    FILE_ALREADY_EXISTS(1),
    FILE_NOT_GENERATED(0);

    operator fun plus(other: GeneratorResult): GeneratorResult =
        if (this.priority <= other.priority) other else this
}