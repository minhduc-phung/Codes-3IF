RANDU <- function(k, p=1, graine)
{
  x <-  rep(graine,k*p+1)
  a <- 65539
  b <- 0
  m <- 2^31
  
  for(i in 2:(k*p+1))
  {
    x[i] <- ((x[i-1]*a)+b)%%m
  }
  x <- matrix(x[2:(k*p+1)],nrow=k,ncol=p)
  return(x)
}

StandardMinimal <- function(k, p=1, graine)
{
  x <-  rep(graine,k*p+1)
  a <- 16807
  b <- 0
  m <- 2^31-1
  
  for(i in 2:(k*p+1))
  {
    x[i] <- ((x[i-1]*a)+b)%%m
  }
  x <- matrix(x[2:(k*p+1)],nrow=k,ncol=p)
  return(x)
}

VonNeumann <- function(n, p=1, graine)
{
  x <-  rep(graine,n*p+1)
  for(i in 2:(n*p+1))
  {
    numbers <- strsplit(format(x[i-1]^2,scientific=FALSE),'')[[1]]
    while(length(numbers)>4){ 
      numbers <- numbers[2:(length(numbers)-1)] 
    }
    x[i] <- as.numeric(numbers)%*%(10^seq(length(numbers)-1,0,-1))
  }
  x <- matrix(x[2:(n*p+1)],nrow=n,ncol=p)
  return(x)
}


MersenneTwister <- function(n, p=1, graine)
{
  set.seed(graine,kind='Mersenne-Twister')
  x <- sample.int(2^32-1,n*p)
  x <- matrix(x,nrow=n,ncol=p)
  return(x)
}

binary <- function(x)
{
  if((x<2^31)&(x>=0))
    return( as.integer(intToBits(as.integer(x))) )
  else{
    if((x<2^32)&(x>0))
      return( c(binary(x-2^31)[1:31], 1) )
    else{
      cat('Erreur dans binary : le nombre etudie n est pas un entier positif en 32 bits.\n')
      return(c())
    }
  }
}

Frequency <- function(x, nb)
{
  res <- 0
  for (i in 1:length(x)) {
    c <- binary(x[i])
    for (j in 1:nb) {
      res <- res + (2*c[j]-1)
    }
  }
  sobs <- abs(res)/sqrt(nb*length(x))
  pvaleur <- 2*(1-pnorm(sobs))
  
  return(pvaleur)
}

Runs <-  function(x,nb)
{
  to <- 2/sqrt(nb*length(x))
  
  #Pre-test
  res <- 0
  for (i in 1:length(x)) {
    c <- binary(x[i])
    for (j in 1:nb) {
      res <- res + c[j]
    }
  }
  
  pi <- res/(nb*length(x))
  if(abs(pi - 1/2) >= to)
  {
    Pvaleur <- 0.0
  }else{
    #Calcul de la statistique Vn
    VN <- 1
    mem <- 0
    for (i in 1:length(x)) {
      c <- binary(x[i])
      if(i != 1 && c[1] != mem){
        VN <- VN +1
      }
      for (j in 1:(nb-1)) {
        if(c[j] != c[j+1])
        {
          VN <- VN +1
        }
        mem <- c[nb]
      }
    }
    
    #Calcul Pvaleur
    Pvaleur <- 2*(1 - pnorm(
      abs(VN-2*nb*length(x)*pi*(1-pi))
      /(2*sqrt(nb*length(x)*pi*(1-pi)))
    ))
  }
  return(Pvaleur)
}

