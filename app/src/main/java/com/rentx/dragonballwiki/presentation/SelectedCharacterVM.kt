package com.rentx.dragonballwiki.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentx.core.local_db.DragonBallCharacterEntity
import com.rentx.dragonballwiki.presentation.favorites.DragonBallFavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SelectedCharacterVM(private val favoritesRepository: DragonBallFavoritesRepository): ViewModel() {

    var character: DragonBallCharacterEntity? = null

    fun onSelectedCharacter(newCharacter: DragonBallCharacterEntity?){
        character = newCharacter
    }

    fun updateCharacter(isFavorite: Boolean) {
        character?.id?.let { id ->
            if (isFavorite) {
                addToFavorites(id)
            } else {
                removeFromFavorites(id)
            }
        }
        character = null
    }

    private fun addToFavorites(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            favoritesRepository.addToFavorites(characterId)
        }
    }

    private fun removeFromFavorites(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            favoritesRepository.removeFromFavorites(characterId)
        }
    }


}