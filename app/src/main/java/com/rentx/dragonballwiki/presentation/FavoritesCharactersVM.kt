package com.rentx.dragonballwiki.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentx.core.local_db.DragonBallCharacterEntity
import com.rentx.dragonballwiki.presentation.favorites.DragonBallFavoritesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesCharactersVM(
    private val repository: DragonBallFavoritesRepository
): ViewModel() {

    private val _favoriteCharacters = MutableStateFlow<List<DragonBallCharacterEntity>>(listOf())
    val favoriteCharacters = _favoriteCharacters.asStateFlow()

    init {
        getFavoriteCharacters()
    }

    private fun getFavoriteCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavoriteCharacters().collect{
                _favoriteCharacters.value = it
            }
        }
    }
    fun addToFavorites(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToFavorites(characterId)
            getFavoriteCharacters()
        }
    }

    fun removeFromFavorites(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromFavorites(characterId)
            getFavoriteCharacters()
        }
    }

}