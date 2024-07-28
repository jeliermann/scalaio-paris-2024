package com.jliermann.scalaio
package usecase.adapted

import Status.*

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.time.LocalDate

class ArticleSpec extends AnyFlatSpec with Matchers:
  "computeStatus" should "label  an article depending on yesterday's state" in {
    val now = LocalDate.of(2024, 11, 7)
    // @formatter:off
    val inputs = Map(
      (None,                                   None)                      -> Unknown,
      (Some(Article(false, now)),              None)                      -> Deleted,
      (Some(Article(true,  now)),              None)                      -> Same,
      (Some(Article(false, now.plusDays(1))),  None)                      -> Deleted,
      (Some(Article(true,  now.plusDays(1))),  None)                      -> Same,
      (Some(Article(false, now.minusDays(1))), None)                      -> Deleted,
      (Some(Article(true,  now.minusDays(1))), None)                      -> Same,
      (None,                                   Some(Article(false, now))) -> Created,
      (Some(Article(false, now)),              Some(Article(false, now))) -> Same,
      (Some(Article(true,  now)),              Some(Article(false, now))) -> Created,
      (Some(Article(false, now.plusDays(1))),  Some(Article(false, now))) -> Unknown,
      (Some(Article(true,  now.plusDays(1))),  Some(Article(false, now))) -> Unknown,
      (Some(Article(false, now.minusDays(1))), Some(Article(false, now))) -> Updated,
      (Some(Article(true,  now.minusDays(1))), Some(Article(false, now))) -> Created,
      (None,                                   Some(Article(true, now)))  -> Same,
      (Some(Article(false, now)),              Some(Article(true, now)))  -> Deleted,
      (Some(Article(true,  now)),              Some(Article(true, now)))  -> Same,
      (Some(Article(false, now.plusDays(1))),  Some(Article(true, now)))  -> Unknown,
      (Some(Article(true,  now.plusDays(1))),  Some(Article(true, now)))  -> Unknown,
      (Some(Article(false, now.minusDays(1))), Some(Article(true, now)))  -> Deleted,
      (Some(Article(true,  now.minusDays(1))), Some(Article(true, now)))  -> Updated
    )
    // @formatter:on

    inputs.foreach((k, v) => Article.computeStatus.tupled(k) shouldBe v)
  }