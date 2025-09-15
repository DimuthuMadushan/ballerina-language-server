package io.ballerina.flowmodelgenerator.extension.request;

import io.ballerina.flowmodelgenerator.core.model.TypeData;

/**
 * Request to check if a type is a subtype of another type.
 *
 * @param filePath       File-path where the types are defined
 * @param sourceType     Name of the source type
 * @param targetType     Name of the target type
 *
 * @since 1.0.0
 */
public record CheckSubtypeRequest(String filePath, TypeData sourceType, TypeData targetType) {
}
