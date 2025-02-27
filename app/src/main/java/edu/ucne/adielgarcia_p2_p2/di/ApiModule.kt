package edu.ucne.adielgarcia_p2_p2.di


    import com.squareup.moshi.Moshi
    import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
    import dagger.Module
    import dagger.Provides
    import dagger.hilt.InstallIn
    import dagger.hilt.components.SingletonComponent
    import retrofit2.Retrofit
    import retrofit2.converter.moshi.MoshiConverterFactory
    import ucne.edu.AdielGarcia_P2_P2.data.remote.entity.EntityManagerApi
    import javax.inject.Singleton

    @InstallIn(SingletonComponent::class)
    @Module
    object ApiModule {
        private const val BASE_URL = "https://sagapi-dev.azurewebsites.net/swagger/index.html"

        @Provides
        @Singleton
        fun providesMoshi(): Moshi =
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()


        @Provides
        @Singleton
        fun providesEntityApi(moshi: Moshi): EntityManagerApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(EntityManagerApi::class.java)
        }
    }
}