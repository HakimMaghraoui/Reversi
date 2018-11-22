import java.util.*;
public class Reversi_IA {
    static Scanner in = new Scanner(System.in);

    public static void initialiser (char [] [] t){
	int i, k;
	for (i=0; i<t[0].length; i++){
	    for (k=0; k<t.length; k++){
			t[k][i]=' ';		
		}
	}
	t[t[0].length/2-1][t.length/2]='n';
	t[t[0].length/2-1][t.length/2-1]='n';
	t[t[0].length/2][t.length/2]='b';
	t[t[0].length/2][t.length/2-1]='b';
	}
    
    public static void initialiser2 (char [] [] t){
		int i, k;
		for (i=0; i<t[0].length; i++){
		    for (k=0; k<t.length; k++){
		    	t[k][i]='n';
		    	if (i==0)
		    		t[k][i]=' ';
		    	if (k==2 && i==7)
		    		t[k][i]='b';
		    }
		} 
	}

    public static void affichage (char [][] t, char [] lettres){
	int i,k,j;
	boolean d;
	d=true;
	for (i=0; i<t[0].length; i++){
		if(t.length>=10)
	        System.out.print("   ");
		else
			System.out.print("  ");
	    for (j=0; j<t.length; j++){
		System.out.print("- ");
	    }
	    System.out.println();
	    if(t.length>=10)
	    	if (t[0].length-i>=10)
	            System.out.print(t[0].length-i+"|");
	    	else
	    		System.out.print(t[0].length-i+" |");
	    else
	    	System.out.print(t[0].length-i+"|");
	    for (k=0; k<t.length; k++){
		System.out.print(t[k][i]+"|");
	    }
	    System.out.println();
	}
	if(t.length>=10)
        System.out.print("   ");
	else
		System.out.print("  ");
	for (j=0; j<t.length; j++){
		System.out.print("- ");
	    }
	System.out.println();
	if(t.length>=10)
        System.out.print("   ");
	else
		System.out.print("  ");
	for (i=0; i<lettres.length && d; i++){
		if (i<t.length)
	    System.out.print(lettres[i]+" ");
		else
			d=false;
	}
	System.out.println();
    }
	   
	public static void partita (char [][] t, char [] lettres, String g1){
		int li;         // lignes input
		char ci;        // colonnes input
		int l, c;		// lignes et colonnes réels		
		boolean v;		//Variable boolean qui me serv a distinguer les jouers, et qui change a chaque tour
		boolean pf;     //Variable qui me serve pour sortir da la boucle au moment que la partie est finie    
		boolean mv1,mv2, mv;
		boolean b1, b2, b3, b4, b;
		boolean d1,d2,d3,d4,d;
		boolean dm;
		boolean p;
		int i,k;
		char g;  //Jouer /n ou b/
		l=0;
		c=0;
		v = false;
		pf = false;
		mv= false; 
		affichage(t,lettres);
		while (pf==false){  // Tante que la partie n'est pas finie /ne sont pas disponibles des placement valide pour les 2 jouers/
			v=!v;   // v=vraie tour de le jour noir, faux pour l'ordi blanc
			if (v)
				g='n';
			else
				g='b';
			dm=true;
			b=false;
		    d=false;
		    for (i=0; i<t[0].length; i++){
			    for (k=0; k<t.length; k++){
			    	b1 = controlloOrizzontale(i,k,t,g);
		            b2 = controlloVerticale(i,k,t,g);
		            b3 = controlloDiagonale31(i,k,t,g);
		            b4 = controlloDiagonale42(i,k,t,g);
		            if ((b1 || b2) || (b3 || b4))
		    	       b=true;
		            d1 = controlloOrizzontale(i,k,t,coloreOpposto(g));
		            d2 =  controlloVerticale(i,k,t,coloreOpposto(g));
		            d3 =  controlloDiagonale31(i,k,t,coloreOpposto(g));
		            d4 = controlloDiagonale42(i,k,t,coloreOpposto(g));
		            if ((d1 || d2) || (d3 || d4))
		    	       d=true;
		            
			    }
			    
		    }
		    if (d == false && b == false){
		    	System.out.println("Il n'y a aucun mouvement pour les joueurs, partie finie. ");
		    	pf=true;
		    	dm=false;
		    }
		    else
		    {
		    	if (b== false){
		    		if (g=='n'){
		    		    System.out.println("Il n'y a aucun mouvement pour le joueur "+g1);
		    		    dm=false;
		    		    }
		    		else{
		    			System.out.println("Il n'y a aucun mouvement pour l'ordi ");
		    			dm=false;
		    			}
		    	}
		    }
		    mv1 = false;
			mv2 = false;
			if (dm){
				mv=false;
		    if (v){
			System.out.println("Tour de "+g1);
			g='n';}
		    else{
		    	System.out.println("Tour de l'ordi");
			g='b';}
			}
			else
				mv=true;
			if (g== 'n'){  // Si est le tour de le jouer noir
		    while (mv==false){ //je controle si effectivment le placement est valide ou pas
		    	p=true;
		    	while(mv1==false){
		    		System.out.print("Saisir la colonne: ");
		    		ci= (in.next()).charAt(0);
		    		for (i=0 ; i<lettres.length && p; i++){ // Boucle pour convertir la colonne input en letres dans une valeur numerique
		    			if (ci==lettres[i] && i<t.length){
				    		c=i;
				    		mv1=true; }	
				    }
		    		if (mv1==false)
		    			System.out.println("Colonne pas valide. ");
		    	}
		    	mv1=false;
		    	while(mv2==false){
		           System.out.print("Saisir la ligne: ");
		           li=in.nextInt();
		           l=t[0].length-li;
		           if(l>=0)
		        	   mv2=true;
		           if (mv2==false)
		    	       System.out.println("Ligne pas valide. ");
		    	}
		    	mv2=false;
		    	mv=true;
		    	if(t[c][l]!= ' '){
			    	System.out.println("Erreur, case déjà occupée. ");
			    	mv=false;
		    	}
    		if (((controlloOrizzontale(l,c,t,g)||controlloVerticale(l,c,t,g))||(controlloDiagonale31(l,c,t,g)||controlloDiagonale42(l,c,t,g)))==false && mv==true){
    			mv=false;
    			System.out.println("Errore, placement pas valide. ");
    		}
		    } 
		    if (dm){ //Si il y a des placements valides
		    	cambioOrizzontale(l,c,t,g);
		    	cambioVerticale(l,c,t,g);
		    	cambioDiagonale31(l,c,t,g);
		    	cambioDiagonale42(l,c,t,g);
		    	affichage(t, lettres);
		    }
			}
			else{  // Tour de l'ordi
				strategiaDue(t,lettres,g);
				// La strategiaUno va a placer le premier pion valide qui trouve, sans une strategie pour gagner
				//La strategiaDue controle tout les placements possibles et compte pour chaque valide les nombre des pions qui va a prendre. Apres il va a placer le pion dans la case avec le nombre majeur de pions
			}
		}	
	}
		    		
	public static void strategiaUno (char [][] t, char [] lettres, char g){
		int i, k;
		boolean mc;
		mc=true;
		for (i=0; i<t[0].length && mc; i++){
		    for (k=0; k<t.length && mc; k++){
	    		if (((controlloOrizzontale(i,k,t,g)||controlloVerticale(i,k,t,g))||(controlloDiagonale31(i,k,t,g)||controlloDiagonale42(i,k,t,g)))==true){
	    			mc=false;
	    			cambioOrizzontale(i,k,t,g);
	    			cambioVerticale(i,k,t,g);
	    			cambioDiagonale31(i,k,t,g);
	    			cambioDiagonale42(i,k,t,g);
	    			affichage(t,lettres);
	    		}
		    }
		}
	}
			   
	public static void strategiaDue (char [][] t, char [] lettres, char g){
		int i, k, s;
		boolean b;
		b=true;
		s= 0;
		for (i=0; i<t[0].length; i++){
		    for (k=0; k<t.length; k++){
	    		if ((((controlloOrizzontale(i,k,t,g)||controlloVerticale(i,k,t,g))||(controlloDiagonale31(i,k,t,g)||controlloDiagonale42(i,k,t,g)))==true) &&  t[k][i]==' '){
		    	if ((contaOrizzontale(i,k,t,g)+contaVerticale(i,k,t,g)+contaDiagonale31(i,k,t,g)+contaDiagonale42(i,k,t,g))>s)
		    		s=contaOrizzontale(i,k,t,g)+contaVerticale(i,k,t,g)+contaDiagonale31(i,k,t,g)+contaDiagonale42(i,k,t,g);
	    		}
		    }
		}
		for (i=0; i<t[0].length && b; i++){
		    for (k=0; k<t.length && b; k++){
	    		if ((((controlloOrizzontale(i,k,t,g)||controlloVerticale(i,k,t,g))||(controlloDiagonale31(i,k,t,g)||controlloDiagonale42(i,k,t,g)))==true)&&  t[k][i]==' '){
		    	if ((contaOrizzontale(i,k,t,g)+contaVerticale(i,k,t,g)+contaDiagonale31(i,k,t,g)+contaDiagonale42(i,k,t,g))==s){
		    		cambioOrizzontale(i,k,t,g);
	    			cambioVerticale(i,k,t,g);
	    			cambioDiagonale31(i,k,t,g);
	    			cambioDiagonale42(i,k,t,g);
	    			affichage(t,lettres);
	    			b=false;
		    	}
	    		}
		    }
		}
	}
	
	public static int contaOrizzontale (int l, int c, char[][] t, char g ){
		int i,k;
		int m;
		boolean d;
		d=true;
		k=0;
		m=0;  //Variable qui compte combien des pions est possible prendre
		for (i=1; (c+i)<t.length && d; i++){
		if (t[c+i][l]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c+i][l]==g && k!=0){
					m=m+k;
					d=false;
				}
				else
					d=false;
	        }
	    }
		d=true;
		k=0;
		for (i=1; (c-i)>=0 && d; i++){
		if (t[c-i][l]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c-i][l]==g && k!=0){
					m=m+k;
					d=false;
				}
				else
					d=false;
	        }
	    }
		return m;
	}
	
	public static int contaVerticale (int l, int c, char[][] t, char g ){
		int i,k;  
		int m;
		boolean d;
		d= true;
		k=0;
		m=0;
		for (i=1; (l+i)<t[0].length && d;i++){
		if (t[c][l+i]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c][l+i]==g && k!=0){
					m=m+k;
					d=false;
				}
				else
					d=false;
	        }
	    }
		d=true;
		k=0;
		for (i=1; ((l-i)>=0) && d; i++){
		if (t[c][l-i]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c][l-i]==g && k!=0){
					m=m+k;
					d=false;
				}
				else
					d=false;
				}
	    }
		return m;
	}
	
	public static int contaDiagonale31 (int l, int c, char[][] t, char g ){
		int io,iv,k;  
		int m;
		boolean d;
		d=true;
		m=0;
		k=0;
		for (io=1 , iv=1; ((c+io)<t.length && (l-iv)>=0) && d; io++, iv++ ){
		if (t[c+io][l-iv]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c+io][l-iv]==g && k!=0){
					m=m+k;
					d=false;
				}
				else
					d=false;
	        }
	    }
		d=true;
		k=0;
		for (io=1 , iv=1; ((c-io)>=0 && (l+iv)<t[0].length)&& d; io++, iv++ ){
		if (t[c-io][l+iv]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c-io][l+iv]==g && k!=0){
					m=m+k;
					d=false;
				}
				else
					d=false;
	        }
	    }
		return m;
	}
	
	public static int contaDiagonale42 (int l, int c, char[][] t, char g ){
		int io,iv,k,m;  
		boolean d;
		d= true;
		m=0;
		k=0;    
		for (io=1 , iv=1; ((c-io)>=0 && (l-iv)>=0) && d; io++, iv++ ){
		if (t[c-io][l-iv]== coloreOpposto(g))
			k++;		
			else{
				if (t[c-io][l-iv]==g && k!=0){
					m=m+k;
					d=false;
				}
				else
					d=false;
	        }
	    }
		d=true;
		k=0;
		for (io=1 , iv=1; ((c+io)<t.length && (l+iv)<t[0].length) && d; io++, iv++ ){
		if (t[c+io][l+iv]== coloreOpposto(g))
			k++;
			else{
				if (t[c+io][l+iv]==g && k!=0){
					m=m+k;
					d=false;
				}
				else
					d=false;
	        }
	    }
		return m;
	}
			
	public static char coloreOpposto (char g){  //méthode qu'il return le couleur opposée
		char co;
		if (g=='n')
			co='b';
		else
			co='n';
		return co;
	}
	
	public static void cambioOrizzontale (int l, int c, char[][] t, char g ){  //méthode qui change les pions dans le sense horizontal
		int i,k,j;  // la variable k compte combien des pions de le couleur opposée il y a
		boolean d;  // il me serve pour sortie da la boucle dans le moment que le placement est pas valide ou quand j'ai dejà change le cases en case que le placement est valide
		d=true;
		k=0;
		for (i=1; (c+i)<t.length && d; i++){ // horizontal droite
		if (t[c+i][l]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c+i][l]==g && k!=0){
					for (j=0; j<=k; j++){
						t[c+j][l]=g;
					}
					d=false;
				}
				else
					d=false;
	        }
	    }
		d=true;
		k=0;
		for (i=1; (c-i)>=0 && d; i++){  // horizontal gauche
		if (t[c-i][l]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c-i][l]==g && k!=0){
					for (j=0; j<=k; j++){
						t[c-j][l]=g;
					}
					d=false;
				}
				else
					d=false;
	        }
	    }
	}
	
	public static boolean controlloOrizzontale (int l, int c, char[][] t, char g ){  //m�thode qu'il controle la validité de le placemente dans le sense horizontal
		int i,k;  
		boolean b1, b2, b3; //ils me serve pour dire si il y a des pions capturables 
		boolean d;
		d=true;
		b1=false;
		b2=false;
		k=0;
		for (i=1; (c+i)<t.length && d;i++){
		if (t[c+i][l]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c+i][l]==g && k!=0){
					b1=true;  // true c-a-d il y a des pions capturables
					d=false;
				}
				else
					d=false;
	        }
	    }
		d=true;
		k=0;
		for (i=1; (c-i)>=0 && d ;i++){
		if (t[c-i][l]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c-i][l]==g && k!=0){
					b2=true;
					d=false;
				}
				else
					d=false;
			}
	    }
		b3=b1||b2;
		if (t[c][l]!= ' ')
			b3=false;
		return b3;   // si les deux sont faux alors il n'y a pas des pions capturables dans le sense horizontal
	}
	
	public static void cambioVerticale (int l, int c, char[][] t, char g ){
		int i,k,j;  
		boolean d;
		d= true;
		k=0;
		for (i=1; (l+i)<t[0].length && d;i++){  // vertical sud
		if (t[c][l+i]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c][l+i]==g && k!=0){
					for (j=0; j<=k; j++){
						t[c][l+j]=g;
					}
					d=false;
				}
				else
					d=false;
	        }
	    }
		d=true;
		k=0;
		for (i=1; (l-i)>=0 && d; i++){  //vertical nord
		if (t[c][l-i]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c][l-i]==g && k!=0){
					for (j=0; j<=k; j++){
						t[c][l-j]=g;
					}
					d=false;
				}
				else
					d=false;
				}
	    }
	}
	
	public static boolean controlloVerticale (int l, int c, char[][] t, char g ){
		int i,k;  
		boolean b1, b2, b3;
		boolean d;
		d= true;
		b1=false;
		b2=false;
		k=0;
		for(i=1; (l+i)<t[0].length && d; i++){
		if (t[c][l+i]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c][l+i]==g && k!=0){
					b1=true;
					d=false;
				}
				else
					d=false;
	        }
	    }
		d= true;
		k=0;
		for (i=1; (l-i)>=0 && d; i++){
		if (t[c][l-i]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c][l-i]==g && k!=0){
					b2=true;
					d=false;
				}
				else
					d=false;
			}
	    }
		b3=b1||b2;
		if (t[c][l]!= ' ')
			b3=false;
		return b3;
	}
	
	public static void cambioDiagonale31 (int l, int c, char[][] t, char g ){
		int io,iv,k,j;  // io compteur horizontale // iv compteur vertical
		boolean d;
		d=true;
		k=0;
		for (io=1 , iv=1; ((c+io)<t.length && (l-iv)>=0) && d; io++, iv++ ){  //diagonal NE
		if (t[c+io][l-iv]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c+io][l-iv]==g && k!=0){
					for (j=0; j<=k; j++){
						t[c+j][l-j]=g;
					}
					d=false;
				}
				else
					d=false;
	        }
	    }
		d=true;
		k=0;
		for (io=1 , iv=1; ((c-io)>=0 && (l+iv)<t[0].length) && d; io++, iv++ ){  //diagonal SW
		if (t[c-io][l+iv]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c-io][l+iv]==g && k!=0){
					for (j=0; j<=k; j++){
						t[c-j][l+j]=g;
					}
					d=false;
				}
				else
					d=false;
	        }
	    }
	}
	
	public static boolean controlloDiagonale31 (int l, int c, char[][] t, char g ){
		int io,iv,k;  
		boolean b1, b2, b3;
		boolean d;
		d=true;
		b1=false;
		b2=false;
		k=0;     
		for (io=1 , iv=1; ((c+io)<t.length && (l-iv)>=0) && d; io++, iv++ ){
		if (t[c+io][l-iv]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c+io][l-iv]==g && k!=0){
					b1=true;
					d=false;
				}
				else
					d=false;
	        }
	    }
		d=true;
		k=0;
		for (io=1 , iv=1; ((c-io)>=0 && (l+iv)<t[0].length) && d; io++, iv++ ){
		if (t[c-io][l+iv]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c-io][l+iv]==g && k!=0){
					b2=true;
					d=false;
				}
				else
					d=false;
	        }
	    }
		b3=b1||b2;
		if (t[c][l]!= ' ')
			b3=false;
		return b3;
	}
	
	public static void cambioDiagonale42 (int l, int c, char[][] t, char g ){
		int io,iv,k,j; 
		boolean d;
		d= true;
		k=0;    
		for (io=1 , iv=1; ((c-io)>=0 && (l-iv)>=0) && d; io++, iv++ ){  //diagonal NW
		if (t[c-io][l-iv]== coloreOpposto(g))
			k++;		
			else{
				if (t[c-io][l-iv]==g && k!=0){
					for (j=0; j<=k; j++){
						t[c-j][l-j]=g;
					}
					d=false;
				}
				else
					d=false;
	        }
	    }
		d=true;
		k=0;
		for (io=1 , iv=1; ((c+io)<t.length && (l+iv)<t[0].length) && d; io++, iv++ ){  //diagonal SE
		if (t[c+io][l+iv]== coloreOpposto(g))
			k++;
			else{
				if (t[c+io][l+iv]==g && k!=0){
					for (j=0; j<=k; j++){
						t[c+j][l+j]=g;
					}
					d=false;
				}
				else
					d=false;
	        }
	    }
	}
	
	public static boolean controlloDiagonale42 (int l, int c, char[][] t, char g ){
		int io,iv,k; 
		boolean b1, b2, b3;
		boolean d;
		d=true;
		b1=false;
		b2=false;
		k=0;
		for (io=1 , iv=1; ((c-io)>=0 && (l-iv)>=0) && d; io++, iv++ ){
		if (t[c-io][l-iv]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c-io][l-iv]==g && k!=0){
					b1=true;
					d=false;
				}
				else
					d=false;
	        }
	    }
		d= true;
		k=0;
		for (io=1 , iv=1; ((c+io)<t.length && (l+iv)<t[0].length) && d; io++, iv++ ){
		if (t[c+io][l+iv]== coloreOpposto(g)){
			k++;
			}
			else{
				if (t[c+io][c+iv]==g && k!=0){
					b2=true;
					d=false;
				}
				else
					d=false;
	        }
	    }
		b3=b1||b2;
		if (t[c][l]!= ' ')
			b3=false;
		return b3;
	}
		
	public static void main (String  [] args){
	int l, c;
	int v;
	int i, k;
	int n,b;    // nombre pions n /noirs/ et b /blancs/
	int pn, pb; // nombre parties gagnes par le noir /pn/ et par le blanc /pb/
	String g1;
	char [] [] t;
	char [] lettres={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'} ;
	v=0;
	pn=0;
	pb=0;
	while (v==0){   // condition pour continuer a jour apres che la partie est finie
    n=0;
	b=0;
	System.out.print("Saisir nombre colonnes: ");
	c=in.nextInt();
	System.out.print("Saisir nombre lignes: ");
	l=in.nextInt();
	t = new char [c] [l];
	System.out.print("Saisir nom jouer (pions noirs) : ");  //le jouer joue toujours avec les pions noirs, l'ordi avec les pions blancs
	g1=in.next();
	initialiser(t);
	partita (t, lettres, g1);
	for (i=0; i<t[0].length; i++){
	    for (k=0; k<t.length; k++){
	    	if ( t[k][i]=='n' )
	    		n++;
	    	else{
	    		if (t[k][i]=='b')
	    			b++;
	    	}
	    		
	    }
	}
	System.out.println("Score noirs ("+g1+"): "+n);
	System.out.println("Score blancs (ordinateur): "+b);
	if (n<b){
		System.out.println("Tu as perdu ");
		pb++;}
	else{
		if (n>b){
			System.out.println("Tu as gagne ");
			pn++;}
		else
			System.out.println("Match nul (les 2 scores sont egaux) ");
	}
	System.out.println("Parties totals gagnes par le noir ("+g1+"): "+pn);
	System.out.println("Parties totals gagnes par le blanc (ordi): "+pb);
	System.out.print("Pour rejouer tapez 0, sinon 1: ");
	v=in.nextInt();
	}
	
    }
}