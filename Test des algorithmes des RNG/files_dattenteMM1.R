FileMM1 <- function(lambda, mu, D)
{
  arrive_temp <- rexp(D, lambda)
  attente_temp <- rexp(D, mu)
  arrive <- c(0)
  
  date_arrive <- arrive_temp[1]
  compteur <- 1
  while(date_arrive < D){
    arrive <- append(arrive, date_arrive)
    compteur <- compteur +1
    date_arrive <- date_arrive + arrive_temp[compteur]
  }
  arrive <- arrive[-1] # Enleve la premiere valeur qui est 0
  
  
  depart <- c(arrive[1] + attente_temp[1])
  for (i in 2:length(arrive)) {
    if(depart[i-1] < D){
      if(depart[(i-1)] > arrive[i]){
        depart <- append(depart, (depart[(i-1)] + attente_temp[i]))
      }else{
        depart <- append(depart, (arrive[i] + attente_temp[i]))
      }
    }else{
      break
    }
  }
  
  if(depart[length(depart)] > D)
  {
    depart <- head(depart, -1)
  }
  return(list(arrive, depart))
}

EvolutionMM1 <- function(arrive, depart)
{
  timestamps <- matrix(0, nrow = 0, ncol = 2)
  #Sauvegarde les dates lorsqu'un client arrive
  for (i in 1:length(arrive))
  {
    timestamps <- rbind(c(arrive[i], 1), timestamps) 
  }
  #Sauvegarde les dates lorsqu'un client sort
  for (i in 1:length(depart))
  {
    timestamps <- rbind(c(depart[i],-1), timestamps) 
  }
  # Trie le matrix par date
  timestamps <- timestamps[order(timestamps[,1], decreasing = FALSE),]
  nb_client <- 0
  for(i in 1:length(timestamps[,1]))
  {
    if(timestamps[i,2] == 1)
    {
      nb_client <- nb_client + 1
      timestamps[i,2] <- nb_client
    } else
    {
      nb_client <- nb_client - 1
      timestamps[i,2] <- nb_client
    }
  }
  return(timestamps)
}

TempDAttente <- function(arrive, depart)
{
  vector_return <- rep(0, length(depart))
  for (i in 1:length(depart))
  {
    vector_return[i] <- depart[i] - arrive[i]
  }
  return(vector_return)
}