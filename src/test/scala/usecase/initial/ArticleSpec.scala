package com.jliermann.scalaio
package usecase.initial

import Availability.*

import org.scalatest.flatspec.AnyFlatSpec
import Status.*

import org.scalatest.matchers.should.Matchers

class ArticleSpec extends AnyFlatSpec with Matchers:
  "computeStatus" should "label  an article depending on yesterday's state" in {
    // @formatter:off
    val inputs = Map(
      (None,                             None)                             -> Unknown,
      (Some(Article(false, Available)),  None)                             -> Deleted,
      (Some(Article(true,  Available)),  None)                             -> Same,
      (Some(Article(false, OutOfStock)), None)                             -> Deleted,
      (Some(Article(true,  OutOfStock)), None)                             -> Same,
      (Some(Article(false, Delisted)),   None)                             -> Deleted,
      (Some(Article(true,  Delisted)),   None)                             -> Same,
      (None,                             Some(Article(false, Available)))  -> Created,
      (Some(Article(false, Available)),  Some(Article(false, Available)))  -> Same,
      (Some(Article(true,  Available)),  Some(Article(false, Available)))  -> Created,
      (Some(Article(false, OutOfStock)), Some(Article(false, Available)))  -> Updated,
      (Some(Article(true,  OutOfStock)), Some(Article(false, Available)))  -> Created,
      (Some(Article(false, Delisted)),   Some(Article(false, Available)))  -> Updated,
      (Some(Article(true,  Delisted)),   Some(Article(false, Available)))  -> Created,
      (None,                             Some(Article(true, Available)))   -> Same,
      (Some(Article(false, Available)),  Some(Article(true, Available)))   -> Deleted,
      (Some(Article(true,  Available)),  Some(Article(true, Available)))   -> Same,
      (Some(Article(false, OutOfStock)), Some(Article(true, Available)))   -> Deleted,
      (Some(Article(true,  OutOfStock)), Some(Article(true, Available)))   -> Updated,
      (Some(Article(false, Delisted)),   Some(Article(true, Available)))   -> Deleted,
      (Some(Article(true,  Delisted)),   Some(Article(true, Available)))   -> Updated,
      (None,                             Some(Article(false, OutOfStock))) -> Created,
      (Some(Article(false, Available)),  Some(Article(false, OutOfStock))) -> Updated,
      (Some(Article(true,  Available)),  Some(Article(false, OutOfStock))) -> Created,
      (Some(Article(false, OutOfStock)), Some(Article(false, OutOfStock))) -> Same,
      (Some(Article(true,  OutOfStock)), Some(Article(false, OutOfStock))) -> Created,
      (Some(Article(false, Delisted)),   Some(Article(false, OutOfStock))) -> Updated,
      (Some(Article(true,  Delisted)),   Some(Article(false, OutOfStock))) -> Created,
      (None,                             Some(Article(true, OutOfStock)))  -> Same,
      (Some(Article(false, Available)),  Some(Article(true, OutOfStock)))  -> Deleted,
      (Some(Article(true,  Available)),  Some(Article(true, OutOfStock)))  -> Updated,
      (Some(Article(false, OutOfStock)), Some(Article(true, OutOfStock)))  -> Deleted,
      (Some(Article(true,  OutOfStock)), Some(Article(true, OutOfStock)))  -> Same,
      (Some(Article(false, Delisted)),   Some(Article(true, OutOfStock)))  -> Deleted,
      (Some(Article(true,  Delisted)),   Some(Article(true, OutOfStock)))  -> Updated,
      (None,                             Some(Article(false, Delisted)))   -> Created,
      (Some(Article(false, Available)),  Some(Article(false, Delisted)))   -> Updated,
      (Some(Article(true,  Available)),  Some(Article(false, Delisted)))   -> Created,
      (Some(Article(false, OutOfStock)), Some(Article(false, Delisted)))   -> Updated,
      (Some(Article(true,  OutOfStock)), Some(Article(false, Delisted)))   -> Created,
      (Some(Article(false, Delisted)),   Some(Article(false, Delisted)))   -> Same,
      (Some(Article(true,  Delisted)),   Some(Article(false, Delisted)))   -> Created,
      (None,                             Some(Article(true, Delisted)))    -> Same,
      (Some(Article(false, Available)),  Some(Article(true, Delisted)))    -> Deleted,
      (Some(Article(true,  Available)),  Some(Article(true, Delisted)))    -> Updated,
      (Some(Article(false, OutOfStock)), Some(Article(true, Delisted)))    -> Deleted,
      (Some(Article(true,  OutOfStock)), Some(Article(true, Delisted)))    -> Updated,
      (Some(Article(false, Delisted)),   Some(Article(true, Delisted)))    -> Deleted,
      (Some(Article(true,  Delisted)),   Some(Article(true, Delisted)))    -> Same
    )
    // @formatter:on

    inputs.foreach((k, v) => Article.computeStatus.tupled(k) shouldBe v)
  }