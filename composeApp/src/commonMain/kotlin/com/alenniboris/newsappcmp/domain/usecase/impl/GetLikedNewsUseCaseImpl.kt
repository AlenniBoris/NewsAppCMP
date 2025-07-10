package com.alenniboris.newsappcmp.domain.usecase.impl

import com.alenniboris.newsappcmp.domain.model.ArticleModelDomain
import com.alenniboris.newsappcmp.domain.model.IAppDispatchers
import com.alenniboris.newsappcmp.domain.repository.IDatabaseRepository
import com.alenniboris.newsappcmp.domain.usecase.logic.IGetLikedNewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.shareIn

class GetLikedNewsUseCaseImpl(
    private val databaseRepository: IDatabaseRepository,
    private val dispatchers: IAppDispatchers
) : IGetLikedNewsUseCase {

    override val likedFlow: SharedFlow<List<ArticleModelDomain>> =
        databaseRepository.getLikedNews()
            .buffer(onBufferOverflow = BufferOverflow.DROP_OLDEST)
            .distinctUntilChanged()
            .shareIn(
                scope = CoroutineScope(SupervisorJob() + dispatchers.IO),
                started = SharingStarted.WhileSubscribed(20_000, 0),
                replay = 1
            )

}