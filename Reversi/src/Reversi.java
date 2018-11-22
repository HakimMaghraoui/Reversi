import java.util.*;
public class Reversi {
    static Scanner in = new Scanner(System.in);

    public static void initialiser (char [] [] t){
	int i, k;
	for (i=0; i<t[0].length; i++){
	    for (k=0; k<t.length; k++){
		if ( k==3 && i==3 || k==3 && i==4){
		    t[k][i]='n';
		} else {
		    if ( k==4 && i==3 || k==4 && i==4){
			t[k][i]='b';
		    } else {
			t[k][i]=' ';
		}
		}
	    }
	    }
	}

    public static void affichage (char [][] t, char [] lettres){
	int i,k,j;
	for (i=0; i<t[0].length; i++){
	    System.out.print("  ");
	    for (j=0; j<t.length; j++){
		System.out.print("- ");
	    }
	    System.out.println();
	    System.out.print(8-i+"|");
	    for (k=0; k<t.length; k++){
		System.out.print(t[k][i]+"|");
	    }
	    System.out.println();
	}
	System.out.print("  ");
	for (j=0; j<t.length; j++){
		System.out.print("- ");
	    }
	System.out.println();
	System.out.print("  ");
	for (i=0; i<lettres.length; i++){
	    System.out.print(lettres[i]+" ");
	}
	System.out.println();
    }
	
	public static void partita (char [][] t, char [] lettres, String g1, String g2){
		int li;         // lignes input, hanno valore di 1 in più rispetto alla linea nel vettore  
		char ci;        // colonnes input, dovrai fare la corrispondenza  letter-numero nel vettore Lettres
		int l, c;		// linea e colonna reali		
		boolean v;		//Variabile booleana che mi serve a distinguere i 2 giocatori, e a fare cambiare di turno quindi
		boolean pf;     //Partita Finita, mi serve per uscire dal ciclo quando la partita finisce     
		boolean mv1,mv2, mv;
		boolean b1, b2, b3, b4, b;
		boolean d1,d2,d3,d4,d;
		boolean dm;
		int i,k;
		char g;  //Giocatore /n o b/
		int [] lignes = {8,7,6,5,4,3,2,1};
		l=0;
		c=0;
		v = false;
		pf = false;
		mv= false; //controllo iniziale validità mossa
		affichage(t,lettres);
		while (pf==false){  // finché la partita non è finita /cioè se non sono disponibili più mosse per nero e bianco/
			v=!v;
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
		    	System.out.println("Non sono disponibili mosse per nessun giocatore, partita finita. ");
		    	pf=true;
		    	dm=false;
		    }
		    else
		    {
		    	if (b== false){
		    		if (g=='n'){
		    		    System.out.println("Non sono disponibili mosse per il giocatore "+g1);
		    		    dm=false;
		    		    }
		    		else{
		    			System.out.println("Non sono disponibili mosse per il giocatore "+g2);
		    			dm=false;
		    			}
		    	}
		    }
		    mv1 = false;
			mv2 = false;
			if (dm){
				mv=false;
		    if (v){
			System.out.println("Turno di "+g1);
			g='n';}
		    else{
			System.out.println("Turno di "+g2);
			g='b';}
			}
			else
				mv=true;
		    while (mv==false){ //controllo se effettivamente gli input sono esistenti nel gioco, e senno continuo a richiedere riga e colonna
		    	while(mv1==false){
		    		System.out.print("Inserire la colonna: ");
		    		ci= (in.next()).charAt(0);
		    		for (i=0 ; i<lettres.length; i++){ // Ciclo per convertire la colonna in lettera nelle colonna in numero nel vettore
				    	if (ci==lettres[i]){
				    		c=i;
				    		mv1=true; }	
				    }
		    		if (mv1==false)
		    			System.out.println("Colonna non valida. ");
		    	}
		    	mv1=false;
		    	while(mv2==false){
		           System.out.print("Inserire la riga: ");
		           li=in.nextInt();
		    for (i=0; i< lignes.length ; i++){ // Ciclo per convertire le lignes input nelle linee corrispondenti nel vettore
		    	if (li == lignes[i]){
		    		l=i;
		    	    mv2=true; }
		    
		    }
		    if (mv2==false)
		    	System.out.println("Riga non valida. ");
		    	}
		    	mv2=false;
		    	mv=true;
		    	if(t[c][l]!= ' '){
			    	System.out.println("Errore, casella già occupata. ");
			    	mv=false;
		    	}
    		if (((controlloOrizzontale(l,c,t,g)||controlloVerticale(l,c,t,g))||(controlloDiagonale31(l,c,t,g)||controlloDiagonale42(l,c,t,g)))==false && mv==true){
    			mv=false;
    			System.out.println("Errore, mossa non valida. ");
    		}
		    } //fine ciclo per la validità iniziale. A questo punto so che la riga e la colonna esistono e sono nel tavolo di gioco
		    // Controllo se sono disponibili mosse
		    ////////////////
		    if (dm){
		    	cambioOrizzontale(l,c,t,g);
		    	cambioVerticale(l,c,t,g);
		    	cambioDiagonale31(l,c,t,g);
		    	cambioDiagonale42(l,c,t,g);
		    	affichage(t, lettres);
		    }
		}	
	}
		    	
		
		    
			   
	
			
	public static char coloreOpposto (char g){
		char co;
		if (g=='n')
			co='b';
		else
			co='n';
		return co;
	}
	
	
	public static void cambioOrizzontale (int l, int c, char[][] t, char g ){
		int i,k,j;  // k conta quante pedine devo cambiare
		boolean d;
		d=true;
		k=0;
		for (i=1; (c+i)<t.length && d; i++){
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
		for (i=1; (c-i)>=0 && d; i++){
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
	
	public static boolean controlloOrizzontale (int l, int c, char[][] t, char g ){
		int i,k;  // k conta quante pedine devo cambiare
		boolean b1, b2, b3;
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
					b1=true;  // true cioè sono disponibili mosse
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
		return b3;   // se entrambi sono falsi allora non sono disponibili mosse
	}
	
	public static void cambioVerticale (int l, int c, char[][] t, char g ){
		int i,k,j;  // k conta quante pedine devo cambiare
		boolean d;
		d= true;
		k=0;
		for (i=1; (l+i)<t[0].length && d;i++){
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
		for (i=1; (l-i)>=0 && d; i++){
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
		int i,k;  // k conta quante pedine devo cambiare
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
		int io,iv,k,j;  // k conta quante pedine devo cambiare // io contatore orizzontale // iv contatore verticale
		boolean d;
		d=true;
		k=0;
		for (io=1 , iv=1; ((c+io)<t.length && (l-iv)>=0) && d; io++, iv++ ){
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
		for (io=1 , iv=1; ((c-io)>=0 && (l+iv)<t[0].length) && d; io++, iv++ ){
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
		int io,iv,k;  // k conta quante pedine devo cambiare // io contatore orizzontale // iv contatore verticale
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
		int io,iv,k,j;  // k conta quante pedine devo cambiare // io contatore orizzontale // iv contatore verticale
		boolean d;
		d= true;
		k=0;    
		for (io=1 , iv=1; ((c-io)>=0 && (l-iv)>=0) && d; io++, iv++ ){
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
		for (io=1 , iv=1; ((c+io)<t.length && (l+iv)<t[0].length) && d; io++, iv++ ){
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
		int io,iv,k;  // k conta quante pedine devo cambiare // io contatore orizzontale // iv contatore verticale
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
	int n,b;    // numero pedine n e b
	int pn, pb; //numero partite vinte dal nero e dal bianco
	String g1, g2;
	char [] [] t;
	char [] lettres={'A','B','C','D','E','F','G','H'} ;
	v=0;
	pn=0;
	pb=0;
	n=0;
	b=0;
	while (v==0){   // Condizione per continuare a giocare dopo che la partita è finita
    c=8;
	l=8;
	t = new char [c] [l];
	System.out.print("Inserire nome giocatore nero: ");
	g1=in.next();
	System.out.print("Inserire nome giocatore bianco: ");
	g2=in.next();
	initialiser(t);
	partita (t, lettres, g1, g2);
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
	System.out.println("Score neri: "+n);
	System.out.println("Score bianchi: "+b);
	if (n<b){
		System.out.println("Vince il bianco ");
		pb++;}
	else{
		if (n>b){
			System.out.println("Vince il nero ");
			pn++;}
		else
			System.out.println("Pareggio ");
	}
	System.out.println("Partite totali vinte dai neri: "+pn);
	System.out.println("Partite totali vinte dai bianchi: "+pb);
	pn=0;
	pb=0;
	System.out.print("Per rigiocare inserire 0, altrimenti 1: ");
	v=in.nextInt();
	}
	
    }
	
	public static void inizializzazione2 (char [] [] t){
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
		t[0][0]='n';
		t[1][0]='b';
		t[5][0]='n';
		t[6][0]='b';
	}
	
}