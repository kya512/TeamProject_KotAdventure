package src.item

import jeu.TirageDes
import personnage.Personnage


class Bombe(nom : String , var nombreDeDes : Int, var maxDe: Int ) : Item(nom){

    //methode indiquant combien de points de dégâts la bombe a fait à la cible.
  fun utiliser(cible:Personnage):Unit {
    var nbDegat = TirageDes(nombreDeDes,maxDe).lance() - cible.calculeTotalDefense()
        if (nbDegat < 1) nbDegat = 1
        println("La bombe à fait $nbDegat de dégat à ${cible.nom}")
  }


}