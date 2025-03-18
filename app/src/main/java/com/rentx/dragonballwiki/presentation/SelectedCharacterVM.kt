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

    private val _character = MutableStateFlow<DragonBallCharacterEntity?>(null)
    var character = _character.asStateFlow()

    fun onSelectedCharacter(newCharacter: DragonBallCharacterEntity?){
        _character.value = newCharacter
    }

    fun addToFavorites(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            favoritesRepository.addToFavorites(characterId)
        }
    }

    fun removeFromFavorites(characterId: Int) {
        viewModelScope.launch(Dispatchers.IO){
            favoritesRepository.removeFromFavorites(characterId)
        }
    }
}