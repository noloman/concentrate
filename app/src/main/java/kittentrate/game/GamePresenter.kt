package kittentrate.game

import android.widget.ViewFlipper
import kittentrate.data.repository.GameRepository
import kittentrate.score.PlayerScore
import kittentrate.utils.Constants

/**
 * Created by Manuel Lorenzo on 18/03/2017.
 */

class GamePresenter internal constructor(private val repository: GameRepository,
                                         private val view: GameContract.View) : GameContract.Presenter {
    var score: Int = 0
        private set
    private val manager: GameDomainContract.Manager

    init {
        this.manager = GameManager(this)
    }

    override fun start() {
        view.showLoadingView()
        getPhotosFromRepo()
    }

    override fun onScoredEntered(playerScore: PlayerScore) {
        if (repository.addTopScore(playerScore) == -1L) {
            view.showErrorView()
        }
    }

    fun shouldDispatchTouchEvent(): Boolean = manager.shouldDispatchUiEvent()

    override fun onItemClicked(position: Int, card: Card, viewFlipper: ViewFlipper) {
        manager.cardFlipped(position, card)
    }

    override fun removeCardsFromMaps() {
        manager.removeCardsFromMap()
        view.removeViewFlipper()
    }

    override fun onTurnCardsOver() {
        view.turnCardsOver()
    }

    override fun notifyAdapterItemRemoved(id: String) {
        view.notifyAdapterItemRemoved(id)
    }

    override fun onKittensMenuItemClicked() {
        repository.preferencesPhotoTag = Constants.PHOTO_TAG_KITTEN_VALUE
        getPhotosFromRepo()
        manager.resetScore()
        view.onScoreChanged(0)
    }

    override fun onPuppiesMenuItemClicked() {
        view.showLoadingView()
        repository.preferencesPhotoTag = Constants.PHOTO_TAG_PUPPY_VALUE
        getPhotosFromRepo()
        manager.resetScore()
        view.onScoreChanged(0)
    }

    private fun getPhotosFromRepo() {
        repository.photos.subscribe(
                { photoEntityList ->
                    run {
                        view.setAdapterData(photoEntityList)
                        view.hideLoadingView()
                    }
                })
    }

    override fun removeViewFlipper() {
        view.removeViewFlipper()
    }

    override fun onGameScoreChanged(gameScore: Int) {
        score = gameScore
        view.onScoreChanged(gameScore)
        view.checkGameFinished()
    }

    override fun notifyAdapterItemChanged(key: Int?) {
        view.notifyAdapterItemChanged(key!!)
    }
}
