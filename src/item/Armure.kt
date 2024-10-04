package src.item
import personnage.Personnage
import item.Qualite


class Armure( nom:String,
              description:String,
              var typeArmure: TypeArmure,
              var  qualite: Qualite
) :Item(nom , description){

    fun calculProtection() :Int{
       return this.typeArmure.bonusType + this.qualite.bonusQualite
    }
    fun utiliser(cible: Personnage){
// a faire plussssseeeeeeeeeeeee tard
    }

}