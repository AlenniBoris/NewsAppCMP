package com.alenniboris.newsappcmp.data.repository

import com.alenniboris.newsappcmp.data.source.local.ArticleDao
import com.alenniboris.newsappcmp.data.source.local.toEntityModel
import com.alenniboris.newsappcmp.data.source.local.toModelDomain
import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.CommonExceptionModelDomain
import com.alenniboris.newsappcmp.domain.model.CustomResultModelDomain
import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import com.alenniboris.newsappcmp.domain.repository.IDatabaseRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.withContext

class DatabaseRepositoryImpl(
    private val dao: ArticleDao,
    private val dispatchers: IAppDispatchers
) : IDatabaseRepository {

    override suspend fun getLikedNews(): CustomResultModelDomain<List<ArticleModelDomain>, CommonExceptionModelDomain> =
        withContext(dispatchers.IO) {
            runCatching {
                val res = dao.getAllArticles().map { it.toModelDomain() }
                return@withContext CustomResultModelDomain.Success(res)
            }.getOrElse {
                Napier.e(tag = "!!!!", message = "error database getting all liked")
                Napier.e(tag = "!!!!", message = it.stackTraceToString())
                return@withContext CustomResultModelDomain.Error(
                    CommonExceptionModelDomain.DatabaseError
                )
            }
        }


    override suspend fun addArticleToDatabase(
        article: ArticleModelDomain
    ): CustomResultModelDomain<Unit, CommonExceptionModelDomain> =
        withContext(dispatchers.IO) {
            runCatching {
                Napier.e(tag = "!!!!", message = "adding")
                dao.addArticleToDatabase(article = article.toEntityModel())
                return@withContext CustomResultModelDomain.Success(Unit)
            }.getOrElse {
                Napier.e(tag = "!!!!", message = "error database adding liked")
                return@withContext CustomResultModelDomain.Error(
                    CommonExceptionModelDomain.DatabaseError
                )
            }
        }

    override suspend fun removeArticleFromDatabase(
        article: ArticleModelDomain
    ): CustomResultModelDomain<Unit, CommonExceptionModelDomain> =
        withContext(dispatchers.IO) {
            runCatching {
                Napier.e(tag = "!!!!", message = "deleting")
                dao.deleteArticleFromDatabase(article = article.toEntityModel())
                return@withContext CustomResultModelDomain.Success(Unit)
            }.getOrElse {
                Napier.e(tag = "!!!!", message = "error database deleting liked")
                return@withContext CustomResultModelDomain.Error(
                    CommonExceptionModelDomain.DatabaseError
                )
            }
        }
}