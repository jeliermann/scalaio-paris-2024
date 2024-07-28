package com.jliermann.scalaio
package usecase.adapted

import java.time.LocalDate
import Status.*

case class Article(isDeleted: Boolean, updateDate: LocalDate)

object Article:
  // Un produit peu disparaître d'un jour à l'autre
  // De même qu'un nouveau produit peu apparaître un jour
  def computeStatus(previousArticle: Option[Article], todayArticle: Option[Article]): Status =
    (previousArticle, todayArticle) match
      case (None, None) => Unknown
      case (Some(Article(_, previousLdt)), Some(Article(_, todayLdt))) if previousLdt.isAfter(todayLdt) => Unknown
      case (previous, today) if previous.fold(true)(_.isDeleted) > today.fold(true)(_.isDeleted) => Created
      case (previous, today) if previous.fold(true)(_.isDeleted) < today.fold(true)(_.isDeleted) => Deleted
      case (Some(Article(_, previousLdt)), Some(Article(_, todayLdt))) if previousLdt.isBefore(todayLdt) => Updated
      case _ => Same

