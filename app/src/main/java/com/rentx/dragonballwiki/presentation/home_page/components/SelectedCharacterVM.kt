package com.rentx.dragonballwiki.presentation.home_page.components

import androidx.lifecycle.ViewModel
import com.rentx.dragonballwiki.model.DragonBallCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedCharacterVM: ViewModel() {

    private val _chosenCharacter = MutableStateFlow<DragonBallCharacter?>(null)
    val chosenCharacter = _chosenCharacter.asStateFlow()

    fun onSelectedCharacter(character: DragonBallCharacter?){
        _chosenCharacter.value = character
    }

}