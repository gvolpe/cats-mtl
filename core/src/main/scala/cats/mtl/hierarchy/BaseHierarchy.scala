package cats
package mtl
package hierarchy

object BaseHierarchy {

  trait BH0 {
    implicit final def askFromLocal[F[_], E](local: ApplicativeLocal[F, E]): ApplicativeAsk[F, E] = local.ask

    implicit final def tellFromListen[F[_], L](listen: FunctorListen[F, L]): FunctorTell[F, L] = listen.tell

    implicit final def raiseFromEmpty[F[_]](empty: FunctorEmpty[F]): FunctorRaise[F, Unit] = {
      new FunctorRaise[F, Unit] {
        val functor: Functor[F] = empty.functor

        def raise[A](e: Unit): F[A] = empty.empty[A]
      }
    }

  }

}

