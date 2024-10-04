package personnage
import jeu.*

class Mage (
    nom: String,
    pointDeVie: Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
): Personnage(nom, pointDeVie, pointDeVieMax, attaque, defense, endurance, vitesse) {


    val grimoire :  MutableList<String> = mutableListOf("Boule de feu" ,"Sort de soins")

    override fun toString() = " Mage ${super.toString()} à dans son grimoire les sorts :  ${grimoire} "

    fun afficheGrimoire(grimoire : MutableList<String>):Boolean{
            grimoire.forEach{println("${grimoire.indexOf(it)} => ${it}")}
    }


    fun choisirEtLancerSort(grimoire : MutableList<String>  ){
        var choixSort = 0
        var choixCible = 0

        val cible : Map<Int,Personnage> = mapOf(1 to Combat.joueur , 2 to Combat.monstre)
        if(grimoire.isEmpty()) {
            println("Le grimoire est vide, vous ne pouvez pas utilisez de sorts")
        } else{
            afficheGrimoire(grimoire) // affiche le grimoire
        }

        if( !grimoire.isEmpty() ) {// si le grimoire n'est pas vide

            while(choixSort !in (1..grimoire.lastIndex)){ //tant que le choix est invalide
                println("Choisis un sort (de 1 à ${grimoire.lastIndex}): ")  //demande à l'utilisateur de choisir un sort
                choixSort = readln().toInt()

                if (choixSort !in (1..grimoire.lastIndex)){
                    println("Choix de sorts invalide")
                }

            }
            choixSort -=1 // pour que l'index soit entre 0 et 1 ( car le choix 2 menera à ne erreur donc on fait 2 -1 pour obtenir le choix numéro 2 )

            println("Qui est ta cible : \n ")

            while(choixCible !in (1..2)) {//tant que le choix est invalide
                cible.forEach{println("${it.key} => ${it.value.nom}")}// affiche le numéro de la cible et son nom
                println("Entre le numéro de ta cible (1 ou 2): ")  //demande à l'utilisateur de choisir une cible
                choixCible = readln().toInt()

                if (choixCible !in (1..2)){
                    println("Choix de cible invalide")
                }
                choixCible -=1 // idem que pour le choix du sort


            }

             var sortChoisi = Sort(grimoire[choixSort] , { caster, cible ->
                 run{
                     var cibleNom = cible.nom
                     val tirageDes = TirageDes(1 , 10)
                     var degat = tirageDes.lance()
                     degat = maxOf(1 , degat - cible.calculeDefense())
                     cible.pointDeVide -= degat
                     println("Le jet d'acide inflige $degat à $cibleNom")
                 }

             }) // création l'object sort
             sortChoisi.effet(grimoire[choixSort] ,cible[choixCible].values)// lance l’effet du sort


        }

        // fin class

    }
}