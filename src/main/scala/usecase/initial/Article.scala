package com.jliermann.scalaio
package usecase.initial

import Status.*

case class Article(isDeleted: Boolean, availability: Availability)

object Article:
  // Un produit peu disparaître d'un jour à l'autre (après avoir eu isDeleted passé à true, ou supprimé directement)
  // De même qu'un nouveau produit peu apparaître un jour
  def computeStatus(previousArticle: Option[Article], todayArticle: Option[Article]): Status =
    (previousArticle, todayArticle) match
      case (None, None) => Unknown
      case (previous, today) if previous.fold(true)(_.isDeleted) > today.fold(true)(_.isDeleted) => Created
      case (previous, today) if previous.fold(true)(_.isDeleted) < today.fold(true)(_.isDeleted) => Deleted
      case (Some(Article(_, previousStatus)), Some(Article(_, todayStatus))) if previousStatus != todayStatus => Updated
      case _ => Same
