package dev.slne.surf.bitmap.generator.generator

enum class GeneratorResult {
    FILE_GENERATED,
    FILE_ALREADY_EXISTS,
    FILE_NOT_GENERATED;

    operator fun plus(other: GeneratorResult): GeneratorResult {
        if (this == FILE_GENERATED && other == FILE_GENERATED) {
            return FILE_GENERATED
        }

        if (this == FILE_NOT_GENERATED || other == FILE_NOT_GENERATED) {
            return FILE_NOT_GENERATED
        }

        if (this == FILE_ALREADY_EXISTS || other == FILE_ALREADY_EXISTS) {
            return FILE_ALREADY_EXISTS
        }

        return FILE_NOT_GENERATED
    }
}