package com.jliermann.scalaio

enum Availability:
  case Available  // Le produit est disponible
  case OutOfStock // Le produit est en rupture de stock
  case Delisted   // Le produit n'est plus fabriqué, écoulement des stocks restants