/**
  * Created by toff on 30/09/2016.
  */

import scala.concurrent.Future

trait GithubService{
  def projects(user:User):Future[Seq[Project]]
  def pullreqs(project:Project):Future[Seq[PullRequest]]
  def watchers(project:Project):Future[Seq[User]]
}

case class User(name:String)
case class Project(name:String, description:String)
case class PullRequest(title:String, project:Project)
case class Statistics(user:User, pullRequest: Seq[PullRequest], watchers:Seq[User])

class StatisticGateway(api:GithubService){

  def getStatistics(user:User):Future[Statistics] = {
    val projects:Future[Seq[Project]] = api.projects(user)
    val reqs = projects flatMap getPRs map(_.flatten)
    val watchers = projects flatMap getWatchers map(_.flatten)
    val together:Future[(Seq[PullRequest], Seq[User])] = reqs zip watchers
    together map {case (r,w) => Statistics(user, r, w)}
  }

  private def getPRs(projects:Seq[Project]):Future[Seq[Seq[PullRequest]]] =
    Future.traverse(projects)(api.pullreqs)

  private def getWatchers(projects:Seq[Project]):Future[Seq[Seq[User]]] =
    Future.traverse(projects)(api.watchers)
}