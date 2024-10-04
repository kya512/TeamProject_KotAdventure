package jeu

import personnage.Mage
import personnage.Personnage
import personnage.Voleur

class Combat(
    val jeu :Jeu,
    val monstre: Personnage,
    val joueur : Personnage
) {
    var nombreTours: Int = 1


    // Méthode pour simuler un tour de combat du joueur
    fun tourDeJoueur() {
        //initialise une liste d'action
        var listeAction =  mapOf<Int, String>(1 to "Attaque" , 2 to "Soin" , 3 to "voler" , 4 to "lancer un sort" , 5 to "passe tour")
        println("\u001B[34m ---Tour de ${this.jeu.joueur.nom} (pv: ${this.jeu.joueur.pointDeVie}) ---")
       //TODO Mission 1.2

        /*1*/  println("Quel action souhaitez-vous utiliser ?\n")
        /*2*/ listeAction.forEach{println("${it.key} : ${it.value}")}
        /*3*/  println("Vous avez le choix entre action (1, 2 , 3, 4 ou 5) :")
        var hero= this.jeu.joueur
        var action = readln().toInt()
         when (action){
             1 -> println("${joueur.nom} attaque ${joueur.attaque} ${this.monstre.nom} et perd ${monstre.pointDeVie}pv")
             2 -> println("${joueur.nom} bois une potion")
             3 -> { if(hero is Voleur){
                 hero.voler(monstre)
             }else{
                 throw WrongCharacterClassException("Action Invalide : Vous n'avez pas la classe Voleur veuillez faire un autre choix")
             }
             }
             4 -> {
                 if (hero is Mage){
                     hero.choisirEtLancerSort()
                 } else{
                     throw WrongCharacterClassException("Action Invalide : Vous n'avez pas la classe Mage veuillez faire un autre choix")
                 }
             }
            else -> println("${joueur.nom} passe son tour")
        }
        this.jeu.joueur.attaque(monstre)
        println("\u001b[0m")
        this.jeu.joueur.passe()
        println("Le ${joueur.nom} passe son tour.")
    }

    // Méthode pour simuler un tour de combat du monstre
    fun tourDeMonstre() {
        println("\u001B[31m---Tour de ${monstre.nom} (pv: ${monstre.pointDeVie}) ---")
        var ran =  (0..101).random()

        if( (0..101).random() <= 10 && monstre.avoirPotion() ){ //a une chance assez faible (10%) de boire une potion.
            monstre.boirePotion()
        }

        if (ran <= 70){ // si le nombre et inférieur à 70 alors le montre attaque , il a 70% de chance d'attaquer
            this.monstre.attaque(this.jeu.joueur)
       } else {
           println("Le monstre passe son tour !")
            }
        println("\u001b[0m")
    }

    // Méthode pour exécuter le combat complet
    fun executerCombat() {
        println("Début du combat : ${this.jeu.joueur.nom} vs ${monstre.nom}")
        //La vitesse indique qui commence
        var tourJoueur = this.jeu.joueur.vitesse >= this.monstre.vitesse
        //Tant que le joueur et le monstre sont vivants
        while (this.jeu.joueur.pointDeVie > 0 && monstre.pointDeVie > 0) {
            println("Tours de jeu : ${nombreTours}")
            if (tourJoueur) {
                tourDeJoueur()
            } else {
                tourDeMonstre()
            }
            nombreTours++
            tourJoueur = !tourJoueur // Alternance des tours entre le joueur et le monstre
            println("${this.jeu.joueur.nom}: ${this.jeu.joueur.pointDeVie} points de vie | ${monstre.nom}: ${monstre.pointDeVie} points de vie")
            println("")
        }

        if (this.jeu.joueur.pointDeVie <= 0) {
            println("Game over ! ${this.jeu.joueur.nom} a été vaincu !")
            println("Le combat recommence")

            this.jeu.joueur.pointDeVie = this.jeu.joueur.pointDeVieMax
            this.monstre.pointDeVie = this.monstre.pointDeVieMax
            this.executerCombat()
        } else {
            println("BRAVO ! ${monstre.nom} a été vaincu !")
        }
    }
}