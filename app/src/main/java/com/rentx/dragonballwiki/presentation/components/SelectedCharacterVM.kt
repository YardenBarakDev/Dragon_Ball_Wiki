package com.rentx.dragonballwiki.presentation.components

import androidx.lifecycle.ViewModel
import com.rentx.dragonballwiki.model.DragonBallCharacter

class SelectedCharacterVM: ViewModel() {

    var character: DragonBallCharacter? = null

    fun onSelectedCharacter(newCharacter: DragonBallCharacter?){
        character = newCharacter
    }
}