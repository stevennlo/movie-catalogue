package com.example.moviecatalogue.data

import com.example.moviecatalogue.graphql.MediaQuery
import com.example.moviecatalogue.graphql.MediasQuery
import com.example.moviecatalogue.graphql.type.CharacterRole
import com.example.moviecatalogue.graphql.type.MediaFormat
import com.example.moviecatalogue.graphql.type.MediaSeason
import java.util.*

object DummyData {
    fun getYear() = 2020
    fun getSeason() = MediaSeason.SPRING
    fun getFormat() = MediaFormat.TV
    fun getMediaId() = 110733
    fun getCalendar(): Calendar = Calendar.getInstance().apply {
        set(2021, 3, 10)
    }

    fun getMediasBody() =
        "{\"data\":{\"Page\":{\"__typename\":\"Page\",\"media\":[{\"__typename\":\"Media\",\"id\":103110,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"IDOLiSH7: Second BEAT!\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx103110-tmjraaFRlEt5.jpg\"}},{\"__typename\":\"Media\",\"id\":104647,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Otome Game no Hametsu Flag shika Nai Akuyaku Reijou ni Tensei shiteshimatta\\u2026\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx104647-dMGZSavRxHcM.jpg\"}},{\"__typename\":\"Media\",\"id\":106319,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Hachi-nan tte, Sore wa Nai deshou!\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx106319-LDDmqyV2rs4D.jpg\"}},{\"__typename\":\"Media\",\"id\":107871,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Princess Connect! Re:Dive\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx107871-ZOh7oeDd0kq9.png\"}},{\"__typename\":\"Media\",\"id\":108241,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Gleipnir\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx108241-CZemOTbuE0Oj.jpg\"}},{\"__typename\":\"Media\",\"id\":108266,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Tsugu Tsugumomo\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx108266-XIxj8RVcidLX.jpg\"}},{\"__typename\":\"Media\",\"id\":108629,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Kitsutsuki Tantei Dokoro\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/b108629-EpbvHgHf1HDZ.jpg\"}},{\"__typename\":\"Media\",\"id\":109019,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Houkago Teibou Nisshi\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx109019-xlrVQRdo1EQi.jpg\"}},{\"__typename\":\"Media\",\"id\":109020,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Yesterday wo Utatte\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx109020-sRBusiVXbsLH.jpg\"}},{\"__typename\":\"Media\",\"id\":109856,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"LISTENERS\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx109856-zDYLvuF4Vuno.jpg\"}},{\"__typename\":\"Media\",\"id\":110130,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Tamayomi\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx110130-QqvIQ5B2X3EJ.jpg\"}},{\"__typename\":\"Media\",\"id\":110458,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Shironeko Project: ZERO CHRONICLE\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx110458-Fnh88lYo5pfl.jpg\"}}]}}}"

    fun getEmptyMediasBody() =
        "{\"data\":{\"Page\":{\"__typename\":\"Page\",\"media\":[]}}}"

    fun getMediaBody() =
        "{\"data\":{\"Media\":{\"__typename\":\"Media\",\"id\":107871,\"title\":{\"__typename\":\"MediaTitle\",\"romaji\":\"Princess Connect! Re:Dive\"},\"coverImage\":{\"__typename\":\"MediaCoverImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx107871-ZOh7oeDd0kq9.png\",\"color\":\"#e48635\"},\"episodes\":13,\"description\":\"Based on an anime RPG smartphone game by Cygames.<br>\\n<br>\\nSet in the lovely land of Astoria where a gentle wind blows, <i>Princess Connect! Re:Dive<\\/i> follows the adventures of: Yuuki, an amnesiac young man; Kokkoro, a small guide who looks after Yuuki; Pecorine, a beautiful swordswoman who is constantly hungry; and Karyl, a cat-eared magical girl with a somewhat cool personality. These fateful four form a guild entitled \\\"Bishoku-den\\\" (\\\"Epicurean Hall\\\") and embark on delicious adventures together.<br>\\n<br>\\n(Source: Crunchyroll)\",\"bannerImage\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/banner\\/107871-Pfc2rj2u8177.jpg\",\"duration\":24,\"genres\":[\"Adventure\",\"Comedy\",\"Fantasy\"],\"averageScore\":70,\"favourites\":688,\"characters\":{\"__typename\":\"CharacterConnection\",\"edges\":[{\"__typename\":\"CharacterEdge\",\"id\":202860,\"role\":\"MAIN\",\"voiceActors\":[{\"__typename\":\"Staff\",\"id\":118510,\"name\":{\"__typename\":\"StaffName\",\"full\":\"Mao Ichimichi\"},\"image\":{\"__typename\":\"StaffImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n118510-iMUv5D7W99C1.png\"},\"languageV2\":\"Japanese\"}],\"node\":{\"__typename\":\"Character\",\"id\":160247,\"name\":{\"__typename\":\"CharacterName\",\"full\":\"Pecorine\"},\"image\":{\"__typename\":\"CharacterImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b160247-MeZX1W0Vgvo1.png\"}}},{\"__typename\":\"CharacterEdge\",\"id\":202861,\"role\":\"MAIN\",\"voiceActors\":[{\"__typename\":\"Staff\",\"id\":119916,\"name\":{\"__typename\":\"StaffName\",\"full\":\"Rika Tachibana\"},\"image\":{\"__typename\":\"StaffImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n119916-Fyw4yUlqOYBh.png\"},\"languageV2\":\"Japanese\"}],\"node\":{\"__typename\":\"Character\",\"id\":160246,\"name\":{\"__typename\":\"CharacterName\",\"full\":\"Karyl\"},\"image\":{\"__typename\":\"CharacterImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b160246-g44lB2ZlqdiT.png\"}}},{\"__typename\":\"CharacterEdge\",\"id\":202862,\"role\":\"MAIN\",\"voiceActors\":[{\"__typename\":\"Staff\",\"id\":118581,\"name\":{\"__typename\":\"StaffName\",\"full\":\"Miku Itou\"},\"image\":{\"__typename\":\"StaffImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n118581-ktr7QHaRxOmG.jpg\"},\"languageV2\":\"Japanese\"}],\"node\":{\"__typename\":\"Character\",\"id\":160248,\"name\":{\"__typename\":\"CharacterName\",\"full\":\"Kokkoro\"},\"image\":{\"__typename\":\"CharacterImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b160248-Q37H6ZX30iwj.png\"}}},{\"__typename\":\"CharacterEdge\",\"id\":202863,\"role\":\"MAIN\",\"voiceActors\":[{\"__typename\":\"Staff\",\"id\":95991,\"name\":{\"__typename\":\"StaffName\",\"full\":\"Atsushi Abe\"},\"image\":{\"__typename\":\"StaffImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n95991-ATKwAqMfrB3L.png\"},\"languageV2\":\"Japanese\"}],\"node\":{\"__typename\":\"Character\",\"id\":160249,\"name\":{\"__typename\":\"CharacterName\",\"full\":\"Yuuki\"},\"image\":{\"__typename\":\"CharacterImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b160249-rqhL0Sai38bV.png\"}}},{\"__typename\":\"CharacterEdge\",\"id\":213167,\"role\":\"SUPPORTING\",\"voiceActors\":[{\"__typename\":\"Staff\",\"id\":114834,\"name\":{\"__typename\":\"StaffName\",\"full\":\"Aya Suzaki\"},\"image\":{\"__typename\":\"StaffImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n114834-WWHMBVvu2Lzu.png\"},\"languageV2\":\"Japanese\"}],\"node\":{\"__typename\":\"Character\",\"id\":166072,\"name\":{\"__typename\":\"CharacterName\",\"full\":\"Karin\"},\"image\":{\"__typename\":\"CharacterImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b166072-yAbZASf1J45y.png\"}}},{\"__typename\":\"CharacterEdge\",\"id\":213168,\"role\":\"SUPPORTING\",\"voiceActors\":[{\"__typename\":\"Staff\",\"id\":119331,\"name\":{\"__typename\":\"StaffName\",\"full\":\"Rie Takahashi\"},\"image\":{\"__typename\":\"StaffImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n119331-0CYDTh2mwy62.png\"},\"languageV2\":\"Japanese\"}],\"node\":{\"__typename\":\"Character\",\"id\":166073,\"name\":{\"__typename\":\"CharacterName\",\"full\":\"Ames\"},\"image\":{\"__typename\":\"CharacterImage\",\"large\":\"https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b166073-segqYcsgs8TZ.png\"}}}]}}}}"

    fun getMedias() = listOf(
        MediasQuery.Medium(id = 103110,
            title = MediasQuery.Title(romaji = "IDOLiSH7: Second BEAT!"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx103110-tmjraaFRlEt5.jpg")),
        MediasQuery.Medium(id = 104647,
            title = MediasQuery.Title(romaji = "Otome Game no Hametsu Flag shika Nai Akuyaku Reijou ni Tensei shiteshimatta\\u2026"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx104647-dMGZSavRxHcM.jpg")),
        MediasQuery.Medium(id = 106319,
            title = MediasQuery.Title(romaji = "Hachi-nan tte, Sore wa Nai deshou!"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx106319-LDDmqyV2rs4D.jpg")),
        MediasQuery.Medium(id = 107871,
            title = MediasQuery.Title(romaji = "Princess Connect! Re:Dive"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx107871-ZOh7oeDd0kq9.png")),
        MediasQuery.Medium(id = 108241,
            title = MediasQuery.Title(romaji = "Gleipnir"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx108241-CZemOTbuE0Oj.jpg")),
        MediasQuery.Medium(id = 108266,
            title = MediasQuery.Title(romaji = "Tsugu Tsugumomo"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx108266-XIxj8RVcidLX.jpg")),
        MediasQuery.Medium(id = 108629,
            title = MediasQuery.Title(romaji = "Kitsutsuki Tantei Dokoro"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/b108629-EpbvHgHf1HDZ.jpg")),
        MediasQuery.Medium(id = 109019,
            title = MediasQuery.Title(romaji = "Houkago Teibou Nisshi"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx109019-xlrVQRdo1EQi.jpg")),
        MediasQuery.Medium(id = 109020,
            title = MediasQuery.Title(romaji = "Yesterday wo Utatte"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx109020-sRBusiVXbsLH.jpg")),
        MediasQuery.Medium(id = 109856,
            title = MediasQuery.Title(romaji = "LISTENERS"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx109856-zDYLvuF4Vuno.jpg")),
        MediasQuery.Medium(id = 110130,
            title = MediasQuery.Title(romaji = "Tamayomi"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx110130-QqvIQ5B2X3EJ.jpg")),
        MediasQuery.Medium(id = 110458,
            title = MediasQuery.Title(romaji = "Shironeko Project: ZERO CHRONICLE"),
            coverImage = MediasQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx110458-Fnh88lYo5pfl.jpg"))
    )

    fun getMedia() = MediaQuery.Media(
        id = 110733,
        title = MediaQuery.Title(romaji = "Princess Connect! Re:Dive"),
        coverImage = MediaQuery.CoverImage(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/cover\\/medium\\/bx107871-ZOh7oeDd0kq9.png",
            color = "#e48635"),
        episodes = 13,
        description = "Based on an anime RPG smartphone game by Cygames.<br>\\n<br>\\nSet in the lovely land of Astoria where a gentle wind blows, <i>Princess Connect! Re:Dive<\\/i> follows the adventures of: Yuuki, an amnesiac young man; Kokkoro, a small guide who looks after Yuuki; Pecorine, a beautiful swordswoman who is constantly hungry; and Karyl, a cat-eared magical girl with a somewhat cool personality. These fateful four form a guild entitled \\\"Bishoku-den\\\" (\\\"Epicurean Hall\\\") and embark on delicious adventures together.<br>\\n<br>\\n(Source: Crunchyroll)",
        bannerImage = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/media\\/anime\\/banner\\/107871-Pfc2rj2u8177.jpg",
        duration = 24,
        genres = listOf("Adventure", "Comedy", "Fantasy"),
        averageScore = 70,
        favourites = 688,
        characters = MediaQuery.Characters(edges = listOf(
            MediaQuery.Edge(
                id = 202860,
                role = CharacterRole.MAIN,
                voiceActors = listOf(
                    MediaQuery.VoiceActor(
                        id = 118510,
                        name = MediaQuery.Name(full = "Mao Ichimichi"),
                        image = MediaQuery.Image(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n118510-iMUv5D7W99C1.png"),
                        languageV2 = "Japanese"
                    )
                ),
                node = MediaQuery.Node(id = 160247,
                    name = MediaQuery.Name1(full = "Pecorine"),
                    image = MediaQuery.Image1(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b160247-MeZX1W0Vgvo1.png"))
            ),
            MediaQuery.Edge(
                id = 202861,
                role = CharacterRole.MAIN,
                voiceActors = listOf(
                    MediaQuery.VoiceActor(
                        id = 119916,
                        name = MediaQuery.Name(full = "Rika Tachibana"),
                        image = MediaQuery.Image(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n119916-Fyw4yUlqOYBh.png"),
                        languageV2 = "Japanese"
                    )
                ),
                node = MediaQuery.Node(id = 160246,
                    name = MediaQuery.Name1(full = "Karyl"),
                    image = MediaQuery.Image1(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b160246-g44lB2ZlqdiT.png"))
            ),
            MediaQuery.Edge(
                id = 202862,
                role = CharacterRole.MAIN,
                voiceActors = listOf(
                    MediaQuery.VoiceActor(
                        id = 118581,
                        name = MediaQuery.Name(full = "Miku Itou"),
                        image = MediaQuery.Image(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n118581-ktr7QHaRxOmG.jpg"),
                        languageV2 = "Japanese"
                    )
                ),
                node = MediaQuery.Node(id = 160248,
                    name = MediaQuery.Name1(full = "Kokkoro"),
                    image = MediaQuery.Image1(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b160248-Q37H6ZX30iwj.png"))
            ),
            MediaQuery.Edge(
                id = 202863,
                role = CharacterRole.MAIN,
                voiceActors = listOf(
                    MediaQuery.VoiceActor(
                        id = 95991,
                        name = MediaQuery.Name(full = "Atsushi Abe"),
                        image = MediaQuery.Image(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n95991-ATKwAqMfrB3L.png"),
                        languageV2 = "Japanese"
                    )
                ),
                node = MediaQuery.Node(id = 160249,
                    name = MediaQuery.Name1(full = "Yuuki"),
                    image = MediaQuery.Image1(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b160249-rqhL0Sai38bV.png"))
            ),
            MediaQuery.Edge(
                id = 213167,
                role = CharacterRole.SUPPORTING,
                voiceActors = listOf(
                    MediaQuery.VoiceActor(
                        id = 114834,
                        name = MediaQuery.Name(full = "Aya Suzaki"),
                        image = MediaQuery.Image(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n114834-WWHMBVvu2Lzu.png"),
                        languageV2 = "Japanese"
                    )
                ),
                node = MediaQuery.Node(id = 166072,
                    name = MediaQuery.Name1(full = "Karin"),
                    image = MediaQuery.Image1(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b166072-yAbZASf1J45y.png"))
            ),
            MediaQuery.Edge(
                id = 213168,
                role = CharacterRole.SUPPORTING,
                voiceActors = listOf(
                    MediaQuery.VoiceActor(
                        id = 119331,
                        name = MediaQuery.Name(full = "Rie Takahashi"),
                        image = MediaQuery.Image(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/staff\\/large\\/n119331-0CYDTh2mwy62.png"),
                        languageV2 = "Japanese"
                    )
                ),
                node = MediaQuery.Node(id = 166073,
                    name = MediaQuery.Name1(full = "Ames"),
                    image = MediaQuery.Image1(large = "https:\\/\\/s4.anilist.co\\/file\\/anilistcdn\\/character\\/large\\/b166073-segqYcsgs8TZ.png"))
            )
        ))
    )
}