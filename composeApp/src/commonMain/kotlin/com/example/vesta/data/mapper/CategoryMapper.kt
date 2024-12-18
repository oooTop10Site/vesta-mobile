package com.example.vesta.data.mapper

import com.example.vesta.data.models.CategoryResponse
import com.example.vesta.data.models.DescriptionResponse
import com.example.vesta.domain.modelsUI.CategoryUi
import com.example.vesta.domain.modelsUI.DescriptionUi
import com.example.vesta.ext.cleanHtml

fun List<CategoryResponse>.toUI(): List<CategoryUi> {
    return map { it.toUI() }
}

//fun CategoryResponse.toUI(): CategoryUi {
//    return CategoryUi(
//        categoryId = this.categoryId ?: 0,
//        image = this.image.orEmpty(),
//        octImage = this.octImage.orEmpty(),
//        parentId = this.parentId ?: 0,
//        top = this.top ?: 0,
//        column = this.column ?: 0,
//        sortOrder = this.sortOrder ?: 0,
//        status = this.status ?: 0,
//        description = this.description?.map { it.toUI() } ?: emptyList(),
//    )
//}
//
fun DescriptionResponse.toUI(): DescriptionUi {
    return DescriptionUi(
        categoryId = this.categoryId ?: 0,
        name = this.name?.cleanHtml().orEmpty(),
        description = this.description.orEmpty(),
        metaKeyword = this.metaKeyword.orEmpty()
    )
}

fun CategoryResponse.toUI(): CategoryUi {
    return CategoryUi(
        categoryId = this.categoryId ?: 0,
        image = this.image.orEmpty(),
        octImage = this.octImage.orEmpty(),
        description =this.description.orEmpty(),
        name =this.name.orEmpty(),
        metaDescription =this.metaDescription.orEmpty(),
        metaTitle =this.metaTitle.orEmpty(),
        metaKeyword =this.metaKeyword.orEmpty(),
    )
}