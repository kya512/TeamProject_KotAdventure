import item.Qualite
import jeu.*
import personnage.*
import  src.item.*

//Créattion des qualités des objets
val qualiteCommun = Qualite("commun", 0, "\u001B[32m")
val qualiteRare = Qualite("rare", 1, couleur = "\u001B[34m")
val qualiteEpic = Qualite("epic", 2, "\u001B[35m")
val qualiteLegendaire = Qualite("legendaire", 3, "\u001B[33m")

//TODO Sprint 1 Mission 2A et 2B Création des types d'armes/armures
val dagueType :TypeArme= TypeArme(1 , 4 , 3 , 18)
val epeeLongueType :TypeArme= TypeArme(1 , 8 , 2, 19)
val arbaleteLegereType :TypeArme= TypeArme(1 , 8 , 2 , 19)

val rembourreType :TypeArmure= TypeArmure("rembourre", 1)
val cuirType :TypeArmure= TypeArmure("cuir",2)
val cuirClouteType :TypeArmure= TypeArmure("cuirCloute", 3)

val dague1 :Arme = Arme("dague +2", "" ,dagueType,qualiteRare)
val dague2 :Arme = Arme("dague +3", "" ,dagueType,qualiteEpic)

val epeeLongue :Arme= Arme("epee Longue ", "" ,epeeLongueType,qualiteCommun)
val arbaleteLegere :Arme=  Arme("arbalete Legere + 3", "" ,arbaleteLegereType,qualiteEpic)

val rembourre :Armure= Armure("rembourre ", "" ,rembourreType,qualiteCommun)
val cuir :Armure= Armure("cuir","" , cuirType , qualiteCommun)
val cuirCloute :Armure= Armure("cuirCloute + 4","", cuirClouteType,qualiteLegendaire)

//TODO Sprint 2 : Création de sorts

val bouleDeFeu = Sort("boule de feu" , { caster: Personnage, cible: Personnage ->
    run{
        //utilisation sur sort boule de feu
        var tirageDes = TirageDes(caster.attaque/3 , 6)
        var degats =  tirageDes.lance() - caster.calculeTotalDefense()

        cible.pointDeVie -= degats
        println("${caster.nom} lance \"boule de feu\" sur ${cible.nom} et lui inflige $degats \n" +
                "pv restant de ${cible.nom} : ${cible.pointDeVie}")
    }
})

val missileMagique = Sort("missile magique" , { caster: Personnage, cible: Personnage  ->//utilisation sur sort boule de feu
    run {
        var tirageDes = TirageDes(1, 6)
        var compteur = 0
        var degats = 0
        while (compteur < (caster.attaque / 2)) {
            degats = tirageDes.lance()
            cible.pointDeVie -= degats
            println("Le projectile magique inflique $degats de dégâts")
            compteur++
        }
    }
})

//Mission 9.B : Création de sorts : Invocation d’une arme magique, Invocation d’une armure magique

val armeMagique = Sort("arme magique" , { caster: Personnage, cible: Personnage ->
    run {
        val tirageDes = TirageDes(1, 20)
        var qualite : Qualite = Qualite()
        val resultatLance = tirageDes.lance()
        when (resultatLance) {
            in (0..5) -> qualite = qualiteCommun
            in (5..10) -> qualite = qualiteRare
            in (10..15) -> qualite = qualiteEpic
            else -> qualite = qualiteLegendaire
        }
        //Creation de l'arme magique de type epée longue
        var armeEpeeLongue = epeeLongue
        cible.inventaire.add(armeEpeeLongue)
        println("Une arme magique est ajouter à l'inventaire")
        cible.armeEquipe = armeEpeeLongue
    }
})

val armureMagique = Sort("armure magique" , { caster: Personnage, cible: Personnage ->
    run {
        val tirageDes = TirageDes(1, 20)
        var qualite : Qualite = Qualite()
        val resultatLance = tirageDes.lance()
        when (resultatLance) {
            in (0..5) -> qualite = qualiteCommun
            in (5..10) -> qualite = qualiteRare
            in (10..15) -> qualite = qualiteEpic
            else -> qualite = qualiteLegendaire
        }
        //Creation de l'arme magique de type epée longue
        var armureEnCuir = cuir
        cible.inventaire.add(cuir)
        println("Une armure magique est ajouter à l'inventaire")
        cible.armureEquipe = cuir
    }
})


//Mission 9.C : Création de sort : Sort de soins
val sortDeSoins = Sort("sort de soins" , { caster: Personnage, cible: Personnage ->
    run {
        var tirageDes = TirageDes(1 , 6)
        var soin =  (caster.attaque /2) + tirageDes.lance()
        cible.pointDeVie += soin
        if (cible.pointDeVie > cible.pointDeVieMax){
            cible.pointDeVie = cible.pointDeVieMax
        }
    }
})



fun main(args: Array<String>) {
    // TODO Intemission 4 : Création des items ( armes, armures potions, bombes )

    //Création des monstres
    val gobelin = Personnage(
        "Gob le gobelin",
        pointDeVie = 20,
        pointDeVieMax = 20,
        attaque = 5,
        defense = 4,
        vitesse = 11,
        endurance = 6)

    // TODO Intermission 1 : Ajouter d'autres monstres
   var listeDeMonstre : MutableList<Personnage> = mutableListOf<Personnage>(
       Personnage("Ame en peine", 30 , 67 ,10, 12, 14, 0),
    Personnage("Armure animée", 20 , 33 ,8, 15, 8, 6),
    Personnage("Ettin", 44 , 88 ,16, 12, 14, 8),
    Personnage("Elementaire du feu", 51 , 102 ,12, 10, 8, 16),
    Personnage("Flagelleyr mental", 35 , 71 ,15, 7, 10, 12),
    Personnage("Gelée ocre", 22 , 45 ,9, 14, 7, 10)
   )


    //TODO On ajoute les monstres a la liste de monstres du jeu
    val jeu = Jeu(listeDeMonstre)
    //Lancement du jeu
    jeu.lancerCombat()
}