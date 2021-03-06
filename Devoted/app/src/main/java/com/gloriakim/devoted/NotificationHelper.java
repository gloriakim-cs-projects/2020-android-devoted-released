package com.gloriakim.devoted;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    //set reading plans
    private String[] ninety = {
        //<editor-fold desc="ninety reading plan"
        "Day 1 - Genesis 1-14",
        "Day 2 - Genesis 15-28",
        "Day 3 - Genesis 29-42",
        "Day 4 - Genesis 43-50; Exodus 1-6",
        "Day 5 - Exodus 7-20",
        "Day 6 - Exodus 21-34",
        "Day 7 - Exodus 35-40; Leviticus 1-8",
        "Day 8 - Leviticus 9-22",
        "Day 9 - Leviticus 23-27; Numbers 1-9",
        "Day 10 - Numbers 10-23",
        "Day 11 - Numbers 24-36; Deuteronomy 1",
        "Day 12 - Deuteronomy 2-15",
        "Day 13 - Deuteronomy 16-29",
        "Day 14 - Deuteronomy 30-34; Joshua 1-9",
        "Day 15 - Joshua 10-23",
        "Day 16 - Joshua 24; Judges 1-13",
        "Day 17 - Judges 14-21; Ruth 1-4; 1 Samuel 1-2",
        "Day 18 - 1 Samuel 3-16",
        "Day 19 - 1 Samuel 17-30",
        "Day 20 - 1 Samuel 31; 2 Samuel 1-12",
        "Day 21 - 2 Samuel 13-24; 1 Kings 1",
        "Day 22 - 1 Kings 2-14",
        "Day 23 - 1 Kings 15-22; 2 Kings 1-5",
        "Day 24 - 2 Kings 6-18",
        "Day 25 - 2 Kings 19-25; 1 Chronicles 1-6",
        "Day 26 - 1 Chronicles 7-19",
        "Day 27 - 1 Chronicles 20-29; 2 Chronicles 1-3",
        "Day 28 - 2 Chronicles 4-16",
        "Day 29 - 2 Chronicles 17-29",
        "Day 30 - 2 Chronicles 30-36; Ezra 1-6",
        "Day 31 - Ezra 7-10; Nehemiah 1-9",
        "Day 32 - Nehemiah 10-13; Esther 1-9",
        "Day 33 - Esther 10; Job 1-12",
        "Day 34 - Job 13-25",
        "Day 35 - Job 26-38",
        "Day 36 - Job 39-42; Psalms 1-9",
        "Day 37 - Psalms 10-22",
        "Day 38 - Psalms 23-35",
        "Day 39 - Psalms 36-48",
        "Day 40 - Psalms 49-61",
        "Day 41 - Psalms 62-74",
        "Day 42 - Psalms 75-87",
        "Day 43 - Psalms 88-100",
        "Day 44 - Psalms 101-113",
        "Day 45 - Psalms 114-126",
        "Day 46 - Psalms 127-139",
        "Day 47 - Psalms 140-150; Proverbs 1-2",
        "Day 48 - Proverbs 3-15",
        "Day 49 - Proverbs 16-28",
        "Day 50 - Proverbs 29-31; Ecclesiastes 1-10",
        "Day 51 - Ecclesiastes 11-12; Song of Solomon 1-8; Isaiah 1-3",
        "Day 52 - Isaiah 4-16",
        "Day 53 - Isaiah 17-29",
        "Day 54 - Isaiah 30-42",
        "Day 55 - Isaiah 43-55",
        "Day 56 - Isaiah 56-66; Jeremiah 1-2",
        "Day 57 - Jeremiah 3-15",
        "Day 58 - Jeremiah 16-28",
        "Day 59 - Jeremiah 29-41",
        "Day 60 - Jeremiah 42-52; Lamentations 1-2",
        "Day 61 - Lamentations 3-5; Ezekiel 1-10",
        "Day 62 - Ezekiel 11-23",
        "Day 63 - Ezekiel 24-36",
        "Day 64 - Ezekiel 37-48; Daniel 1",
        "Day 65 - Daniel 2-12; Hosea 1-2",
        "Day 66 - Hosea 3-14; Joel 1",
        "Day 67 - Joel 2-3; Amos 1-9; Obadiah 1; Jonah 1",
        "Day 68 - Jonah 2-4; Micah 1-7; Nahum 1-3",
        "Day 69 - Habakkuk 1-3; Zephaniah 1-3; Haggai 1-2; Zechariah 1-5",
        "Day 70 - Zechariah 6-14; Malachi 1-4",
        "Day 71 - Matthew 1-13",
        "Day 72 - Matthew 14-26",
        "Day 73 - Matthew 27-28; Mark 1-11",
        "Day 74 - Mark 12-16; Luke 1-8",
        "Day 75 - Luke 9-21",
        "Day 76 - Luke 22-24; John 1-10",
        "Day 77 - John 11-21; Acts 1-2",
        "Day 78 - Acts 3-15",
        "Day 79 - Acts 16-28",
        "Day 80 - Romans 1-13",
        "Day 81 - Romans 14-16; 1 Corinthians 1-10",
        "Day 82 - 1 Corinthians 11-16; 2 Corinthians 1-7",
        "Day 83 - 2 Corinthians 8-13; Galatians 1-6; Ephesians 1",
        "Day 84 - Ephesians 2-6; Philippians 1-4; Colossians 1-4",
        "Day 85 - 1 Thessalonians 1-5; 2 Thessalonians 1-3; 1 Timothy 1-5",
        "Day 86 - 1 Timothy 6; 2 Timothy 1-4; Titus 1-3; Philemon 1; Hebrews 1-4",
        "Day 87 - Hebrews 5-13; James 1-4",
        "Day 88 - James 5; 1 Peter 1-5; 2 Peter 1-3; 1 John 1-4",
        "Day 89 - 1 John 5; 2 John 1; 3 John 1; Jude 1; Revelation 1-9",
        "Day 90 - Revelation 10-22"
                //</editor-fold>
    };
    private String[] bible = {
        //<editor-fold desc="bible order reading plan">
        "Day 1 - Genesis 1-4",
        "Day 2 - Genesis 5-8",
        "Day 3 - Genesis 9-12",
        "Day 4 - Genesis 13-17",
        "Day 5 - Genesis 18-20",
        "Day 6 - Genesis 21-23",
        "Day 7 - Genesis 24-25",
        "Day 8 - Genesis 26-28",
        "Day 9 - Genesis 29-31",
        "Day 10 - Genesis 32-35",
        "Day 11 - Genesis 36-38",
        "Day 12 - Genesis 39-41",
        "Day 13 - Genesis 42-43",
        "Day 14 - Genesis 44-46",
        "Day 15 - Genesis 47-50",
        "Day 16 - Exodus 1-4",
        "Day 17 - Exodus 5-7",
        "Day 18 - Exodus 8-10",
        "Day 19 - Exodus 11-13",
        "Day 20 - Exodus 14-16",
        "Day 21 - Exodus 17-20",
        "Day 22 - Exodus 21-23",
        "Day 23 - Exodus 24-27",
        "Day 24 - Exodus 28-30",
        "Day 25 - Exodus 31-34",
        "Day 26 - Exodus 35-37",
        "Day 27 - Exodus 38-40",
        "Day 28 - Leviticus 1-4",
        "Day 29 - Leviticus 5-7",
        "Day 30 - Leviticus 8-10",
        "Day 31 - Leviticus 11-13",
        "Day 32 - Leviticus 14-15",
        "Day 33 - Leviticus 16-18",
        "Day 34 - Leviticus 19-21",
        "Day 35 - Leviticus 22-23",
        "Day 36 - Leviticus 24-25",
        "Day 37 - Leviticus 26-27",
        "Day 38 - Numbers 1-2",
        "Day 39 - Numbers 3-4",
        "Day 40 - Numbers 5-6",
        "Day 41 - Numbers 7",
        "Day 42 - Numbers 8-10",
        "Day 43 - Numbers 11-13",
        "Day 44 - Numbers 14-15",
        "Day 45 - Numbers 16-18",
        "Day 46 - Numbers 19-21",
        "Day 47 - Numbers 22-24",
        "Day 48 - Numbers 25-26",
        "Day 49 - Numbers 27-29",
        "Day 50 - Numbers 30-32",
        "Day 51 - Numbers 33-36",
        "Day 52 - Deuteronomy 1-2",
        "Day 53 - Deuteronomy 3-4",
        "Day 54 - Deuteronomy 5-8",
        "Day 55 - Deuteronomy 9-11",
        "Day 56 - Deuteronomy 12-15",
        "Day 57 - Deuteronomy 16-19",
        "Day 58 - Deuteronomy 20-22",
        "Day 59 - Deuteronomy 23-25",
        "Day 60 - Deuteronomy 26-27",
        "Day 61 - Deuteronomy 28-29",
        "Day 62 - Deuteronomy 30-32",
        "Day 63 - Deuteronomy 33-34",
        "Day 64 - Joshua 1-4",
        "Day 65 - Joshua 5-7",
        "Day 66 - Joshua 8-10",
        "Day 67 - Joshua 11-13",
        "Day 68 - Joshua 14-17",
        "Day 69 - Joshua 18-20",
        "Day 70 - Joshua 21-22",
        "Day 71 - Joshua 23-24",
        "Day 72 - Judges 1-3",
        "Day 73 - Judges 4-5",
        "Day 74 - Judges 6-8",
        "Day 75 - Judges 9-10",
        "Day 76 - Judges 11-13",
        "Day 77 - Judges 14-16",
        "Day 78 - Judges 17-19",
        "Day 79 - Judges 20-21",
        "Day 80 - Ruth 1-4",
        "Day 81 - 1 Samuel 1-3",
        "Day 82 - 1 Samuel 4-7",
        "Day 83 - 1 Samuel 8-12",
        "Day 84 - 1 Samuel 13-14",
        "Day 85 - 1 Samuel 15-16",
        "Day 86 - 1 Samuel 17-18",
        "Day 87 - 1 Samuel 19-21",
        "Day 88 - 1 Samuel 22-24",
        "Day 89 - 1 Samuel 25-27",
        "Day 90 - 1 Samuel 28-31",
        "Day 91 - 2 Samuel 1-3",
        "Day 92 - 2 Samuel 4-7",
        "Day 93 - 2 Samuel 8-11",
        "Day 94 - 2 Samuel 12-13",
        "Day 95 - 2 Samuel 14-16",
        "Day 96 - 2 Samuel 17-19",
        "Day 97 - 2 Samuel 20-22",
        "Day 98 - 2 Samuel 23-24",
        "Day 99 - 1 Kings 1-2",
        "Day 100 - 1 Kings 3-5",
        "Day 101 - 1 Kings 6-7",
        "Day 102 - 1 Kings 8-9",
        "Day 103 - 1 Kings 10-12",
        "Day 104 - 1 Kings 13-15",
        "Day 105 - 1 Kings 16-18",
        "Day 106 - 1 Kings 19-20",
        "Day 107 - 1 Kings 21-22",
        "Day 108 - 2 Kings 1-3",
        "Day 109 - 2 Kings 4-5",
        "Day 110 - 2 Kings 6-8",
        "Day 111 - 2 Kings 9-10",
        "Day 112 - 2 Kings 11-13",
        "Day 113 - 2 Kings 14-16",
        "Day 114 - 2 Kings 17-18",
        "Day 115 - 2 Kings 19-21",
        "Day 116 - 2 Kings 22-23",
        "Day 117 - 2 Kings 24-25",
        "Day 118 - 1 Chronicles 1-2",
        "Day 119 - 1 Chronicles 3-4",
        "Day 120 - 1 Chronicles 5-6",
        "Day 121 - 1 Chronicles 7-9",
        "Day 122 - 1 Chronicles 10-12",
        "Day 123 - 1 Chronicles 13-16",
        "Day 124 - 1 Chronicles 17-19",
        "Day 125 - 1 Chronicles 20-23",
        "Day 126 - 1 Chronicles 24-26",
        "Day 127 - 1 Chronicles 27-29",
        "Day 128 - 2 Chronicles 1-4",
        "Day 129 - 2 Chronicles 5-7",
        "Day 130 - 2 Chronicles 8-11",
        "Day 131 - 2 Chronicles 12-16",
        "Day 132 - 2 Chronicles 17-20",
        "Day 133 - 2 Chronicles 21-24",
        "Day 134 - 2 Chronicles 25-28",
        "Day 135 - 2 Chronicles 29-31",
        "Day 136 - 2 Chronicles 32-34",
        "Day 137 - 2 Chronicles 35-36",
        "Day 138 - Ezra 1-4",
        "Day 139 - Ezra 5-7",
        "Day 140 - Ezra 8-10",
        "Day 141 - Nehemiah 1-3",
        "Day 142 - Nehemiah 4-7",
        "Day 143 - Nehemiah 8-10",
        "Day 144 - Nehemiah 11-13",
        "Day 145 - Esther 1-5",
        "Day 146 - Esther 6-10",
        "Day 147 - Job 1-4",
        "Day 148 - Job 5-8",
        "Day 149 - Job 9-12",
        "Day 150 - Job 13-16",
        "Day 151 - Job 17-20",
        "Day 152 - Job 21-24",
        "Day 153 - Job 25-30",
        "Day 154 - Job 31-34",
        "Day 155 - Job 35-38",
        "Day 156 - Job 39-42",
        "Day 157 - Psalms 1-8",
        "Day 158 - Psalms 9-17",
        "Day 159 - Psalms 18-21",
        "Day 160 - Psalms 22-27",
        "Day 161 - Psalms 28-33",
        "Day 162 - Psalms 34-37",
        "Day 163 - Psalms 38-42",
        "Day 164 - Psalms 43-49",
        "Day 165 - Psalms 50-55",
        "Day 166 - Psalms 56-61",
        "Day 167 - Psalms 62-68",
        "Day 168 - Psalms 69-72",
        "Day 169 - Psalms 73-77",
        "Day 170 - Psalms 78-80",
        "Day 171 - Psalms 81-88",
        "Day 172 - Psalms 89-94",
        "Day 173 - Psalms 95-103",
        "Day 174 - Psalms 104-106",
        "Day 175 - Psalms 107-111",
        "Day 176 - Psalms 112-118",
        "Day 177 - Psalm 119",
        "Day 178 - Psalms 120-133",
        "Day 179 - Psalms 134-140",
        "Day 180 - Psalms 141-150",
        "Day 181 - Proverbs 1-3",
        "Day 182 - Proverbs 4-7",
        "Day 183 - Proverbs 8-11",
        "Day 184 - Proverbs 12-14",
        "Day 185 - Proverbs 15-17",
        "Day 186 - Proverbs 18-20",
        "Day 187 - Proverbs 21-23",
        "Day 188 - Proverbs 24-26",
        "Day 189 - Proverbs 27-29",
        "Day 190 - Proverbs 30-31",
        "Day 191 - Ecclesiastes 1-4",
        "Day 192 - Ecclesiastes 5-8",
        "Day 193 - Ecclesiastes 9-12",
        "Day 194 - Song of Solomon 1-4",
        "Day 195 - Song of Solomon 5-8",
        "Day 196 - Isaiah 1-3",
        "Day 197 - Isaiah 4-8",
        "Day 198 - Isaiah 9-11",
        "Day 199 - Isaiah 12-14",
        "Day 200 - Isaiah 15-19",
        "Day 201 - Isaiah 20-24",
        "Day 202 - Isaiah 25-28",
        "Day 203 - Isaiah 29-31",
        "Day 204 - Isaiah 32-34",
        "Day 205 - Isaiah 35-37",
        "Day 206 - Isaiah 38-40",
        "Day 207 - Isaiah 41-43",
        "Day 208 - Isaiah 44-46",
        "Day 209 - Isaiah 47-49",
        "Day 210 - Isaiah 50-52",
        "Day 211 - Isaiah 53-56",
        "Day 212 - Isaiah 57-59",
        "Day 213 - Isaiah 60-63",
        "Day 214 - Isaiah 64-66",
        "Day 215 - Jeremiah 1-3",
        "Day 216 - Jeremiah 4-5",
        "Day 217 - Jeremiah 6-8",
        "Day 218 - Jeremiah 9-11",
        "Day 219 - Jeremiah 12-14",
        "Day 220 - Jeremiah 15-17",
        "Day 221 - Jeremiah 18-21",
        "Day 222 - Jeremiah 22-24",
        "Day 223 - Jeremiah 25-27",
        "Day 224 - Jeremiah 28-30",
        "Day 225 - Jeremiah 31-32",
        "Day 226 - Jeremiah 33-36",
        "Day 227 - Jeremiah 37-39",
        "Day 228 - Jeremiah 40-43",
        "Day 229 - Jeremiah 44-46",
        "Day 230 - Jeremiah 47-48",
        "Day 231 - Jeremiah 49",
        "Day 232 - Jeremiah 50",
        "Day 233 - Jeremiah 51-52",
        "Day 234 - Lamentations 1-2",
        "Day 235 - Lamentations 3-5",
        "Day 236 - Ezekiel 1-4",
        "Day 237 - Ezekiel 5-8",
        "Day 238 - Ezekiel 9-12",
        "Day 239 - Ezekiel 13-15",
        "Day 240 - Ezekiel 16-17",
        "Day 241 - Ezekiel 18-20",
        "Day 242 - Ezekiel 21-22",
        "Day 243 - Ezekiel 23-24",
        "Day 244 - Ezekiel 25-27",
        "Day 245 - Ezekiel 28-30",
        "Day 246 - Ezekiel 31-32",
        "Day 247 - Ezekiel 33-35",
        "Day 248 - Ezekiel 36-38",
        "Day 249 - Ezekiel 39-40",
        "Day 250 - Ezekiel 41-43",
        "Day 251 - Ezekiel 44-46",
        "Day 252 - Ezekiel 47-48",
        "Day 253 - Daniel 1-3",
        "Day 254 - Daniel 4-5",
        "Day 255 - Daniel 6-8",
        "Day 256 - Daniel 9-12",
        "Day 257 - Hosea 1-4",
        "Day 258 - Hosea 5-9",
        "Day 259 - Hosea 10-14",
        "Day 260 - Joel 1-3",
        "Day 261 - Amos 1-4",
        "Day 262 - Amos 5-9",
        "Day 263 - Obadiah 1",
        "Day 264 - Jonah 1-4",
        "Day 265 - Micah 1-4",
        "Day 266 - Micah 5-7",
        "Day 267 - Nahum 1-3",
        "Day 268 - Habakkuk 1-3",
        "Day 269 - Zephaniah 1-3",
        "Day 270 - Haggai 1-2",
        "Day 271 - Zechariah 1-5",
        "Day 272 - Zechariah 6-10",
        "Day 273 - Zechariah 11-14",
        "Day 274 - Malachi 1-4",
        "Day 275 - Matthew 1-4",
        "Day 276 - Matthew 5-6",
        "Day 277 - Matthew 7-9",
        "Day 278 - Matthew 10-11",
        "Day 279 - Matthew 12-13",
        "Day 280 - Matthew 14-17",
        "Day 281 - Matthew 18-20",
        "Day 282 - Matthew 21-22",
        "Day 283 - Matthew 23-24",
        "Day 284 - Matthew 25-26",
        "Day 285 - Matthew 27-28",
        "Day 286 - Mark 1-3",
        "Day 287 - Mark 4-5",
        "Day 288 - Mark 6-7",
        "Day 289 - Mark 8-9",
        "Day 290 - Mark 10-11",
        "Day 291 - Mark 12-13",
        "Day 292 - Mark 14",
        "Day 293 - Mark 15-16",
        "Day 294 - Luke 1-2",
        "Day 295 - Luke 3-4",
        "Day 296 - Luke 5-6",
        "Day 297 - Luke 7-8",
        "Day 298 - Luke 9-10",
        "Day 299 - Luke 11-12",
        "Day 300 - Luke 13-15",
        "Day 301 - Luke 16-18",
        "Day 302 - Luke 19-20",
        "Day 303 - Luke 21-22",
        "Day 304 - Luke 23-24",
        "Day 305 - John 1-2",
        "Day 306 - John 3-4",
        "Day 307 - John 5-6",
        "Day 308 - John 7-8",
        "Day 309 - John 9-10",
        "Day 310 - John 11-12",
        "Day 311 - John 13-15",
        "Day 312 - John 16-17",
        "Day 313 - John 18-19",
        "Day 314 - John 20-21",
        "Day 315 - Acts 1-3",
        "Day 316 - Acts 4-5",
        "Day 317 - Acts 6-7",
        "Day 318 - Acts 8-9",
        "Day 319 - Acts 10-11",
        "Day 320 - Acts 12-13",
        "Day 321 - Acts 14-15",
        "Day 322 - Acts 16-17",
        "Day 323 - Acts 18-19",
        "Day 324 - Acts 20-21",
        "Day 325 - Acts 22-23",
        "Day 326 - Acts 24-26",
        "Day 327 - Acts 27-28",
        "Day 328 - Romans 1-3",
        "Day 329 - Romans 4-7",
        "Day 330 - Romans 8-10",
        "Day 331 - Romans 11-14",
        "Day 332 - Romans 15-16",
        "Day 333 - 1 Corinthians 1-4",
        "Day 334 - 1 Corinthians 5-9",
        "Day 335 - 1 Corinthians 10-13",
        "Day 336 - 1 Corinthians 14-16",
        "Day 337 - 2 Corinthians 1-4",
        "Day 338 - 2 Corinthians 5-9",
        "Day 339 - 2 Corinthians 10-13",
        "Day 340 - Galatians 1-3",
        "Day 341 - Galatians 4-6",
        "Day 342 - Ephesians 1-3",
        "Day 343 - Ephesians 4-6",
        "Day 344 - Philippians 1-4",
        "Day 345 - Colossians 1-4",
        "Day 346 - 1 Thessalonians 1-5",
        "Day 347 - 2 Thessalonians 1-3",
        "Day 348 - 1 Timothy 1-6",
        "Day 349 - 2 Timothy 1-4",
        "Day 350 - Philemon 1; Titus 1-3",
        "Day 351 - Hebrews 1-4",
        "Day 352 - Hebrews 5-8",
        "Day 353 - Hebrews 9-10",
        "Day 354 - Hebrews 11-13",
        "Day 355 - James 1-5",
        "Day 356 - 1 Peter 1-5; 2 Peter 1-3",
        "Day 357 - 1 John 1-5",
        "Day 358 - 2 John 1; 3 John 1; Jude 1",
        "Day 359 - Revelation 1-3",
        "Day 360 - Revelation 4-7",
        "Day 361 - Revelation 8-11",
        "Day 362 - Revelation 12-14",
        "Day 363 - Revelation 15-17",
        "Day 364 - Revelation 18-19",
        "Day 365 - Revelation 20-22"
                //</editor-fold>
    };
    private String[] history = {
        //<editor-fold desc="history order reading plan">
        "Day 1 - Genesis 1-3",
        "Day 2 - Genesis 4-7",
        "Day 3 - Genesis 8-11",
        "Day 4 - Job 1-5",
        "Day 5 - Job 6-9",
        "Day 6 - Job 10-13",
        "Day 7 - Job 14-16",
        "Day 8 - Job 17-20",
        "Day 9 - Job 21-23",
        "Day 10 - Job 24-28",
        "Day 11 - Job 29-31",
        "Day 12 - Job 32-34",
        "Day 13 - Job 35-37",
        "Day 14 - Job 38-39",
        "Day 15 - Job 40-42",
        "Day 16 - Genesis 12-15",
        "Day 17 - Genesis 16-18",
        "Day 18 - Genesis 19-21",
        "Day 19 - Genesis 22-24",
        "Day 20 - Genesis 25-26",
        "Day 21 - Genesis 27-29",
        "Day 22 - Genesis 30-31",
        "Day 23 - Genesis 32-34",
        "Day 24 - Genesis 35-37",
        "Day 25 - Genesis 38-40",
        "Day 26 - Genesis 41-42",
        "Day 27 - Genesis 43-45",
        "Day 28 - Genesis 46-47",
        "Day 29 - Genesis 48-50",
        "Day 30 - Exodus 1-3",
        "Day 31 - Exodus 4-6",
        "Day 32 - Exodus 7-9",
        "Day 33 - Exodus 10-12",
        "Day 34 - Exodus 13-15",
        "Day 35 - Exodus 16-18",
        "Day 36 - Exodus 19-21",
        "Day 37 - Exodus 22-24",
        "Day 38 - Exodus 25-27",
        "Day 39 - Exodus 28-29",
        "Day 40 - Exodus 30-32",
        "Day 41 - Exodus 33-35",
        "Day 42 - Exodus 36-38",
        "Day 43 - Exodus 39-40",
        "Day 44 - Leviticus 1-4",
        "Day 45 - Leviticus 5-7",
        "Day 46 - Leviticus 8-10",
        "Day 47 - Leviticus 11-13",
        "Day 48 - Leviticus 14-15",
        "Day 49 - Leviticus 16-18",
        "Day 50 - Leviticus 19-21",
        "Day 51 - Leviticus 22-23",
        "Day 52 - Leviticus 24-25",
        "Day 53 - Leviticus 26-27",
        "Day 54 - Numbers 1-2",
        "Day 55 - Numbers 3-4",
        "Day 56 - Numbers 5-6",
        "Day 57 - Numbers 7",
        "Day 58 - Numbers 8-10",
        "Day 59 - Numbers 11-13",
        "Day 60 - Numbers 14-15; Psalm 90",
        "Day 61 - Numbers 16-17",
        "Day 62 - Numbers 18-20",
        "Day 63 - Numbers 21-22",
        "Day 64 - Numbers 23-25",
        "Day 65 - Numbers 26-27",
        "Day 66 - Numbers 28-30",
        "Day 67 - Numbers 31-32",
        "Day 68 - Numbers 33-34",
        "Day 69 - Numbers 35-36",
        "Day 70 - Deuteronomy 1-2",
        "Day 71 - Deuteronomy 3-4",
        "Day 72 - Deuteronomy 5-7",
        "Day 73 - Deuteronomy 8-10",
        "Day 74 - Deuteronomy 11-13",
        "Day 75 - Deuteronomy 14-16",
        "Day 76 - Deuteronomy 17-20",
        "Day 77 - Deuteronomy 21-23",
        "Day 78 - Deuteronomy 24-27",
        "Day 79 - Deuteronomy 28-29",
        "Day 80 - Deuteronomy 30-31",
        "Day 81 - Deuteronomy 32-34; Psalm 91",
        "Day 82 - Joshua 1-4",
        "Day 83 - Joshua 5-8",
        "Day 84 - Joshua 9-11",
        "Day 85 - Joshua 12-15",
        "Day 86 - Joshua 16-18",
        "Day 87 - Joshua 19-21",
        "Day 88 - Joshua 22-24",
        "Day 89 - Judges 1-2",
        "Day 90 - Judges 3-5",
        "Day 91 - Judges 6-7",
        "Day 92 - Judges 8-9",
        "Day 93 - Judges 10-12",
        "Day 94 - Judges 13-15",
        "Day 95 - Judges 16-18",
        "Day 96 - Judges 19-21",
        "Day 97 - Ruth 1-4",
        "Day 98 - 1 Samuel 1-3",
        "Day 99 - 1 Samuel 4-8",
        "Day 100 - 1 Samuel 9-12",
        "Day 101 - 1 Samuel 13-14",
        "Day 102 - 1 Samuel 15-17",
        "Day 103 - 1 Samuel 18-20; Psalm 11, 59",
        "Day 104 - 1 Samuel 21-24",
        "Day 105 - Psalm 7, 27, 31, 34, 52",
        "Day 106 - Psalm 56, 120, 140-142",
        "Day 107 - 1 Samuel 25-27",
        "Day 108 - Psalm 17, 35, 54, 63",
        "Day 109 - 1 Samuel 28-31; Psalm 18",
        "Day 110 - Psalm 121, 123-125, 128-130",
        "Day 111 - 2 Samuel 1-4",
        "Day 112 - Psalm 6, 8-10, 14, 16, 19, 21",
        "Day 113 - 1 Chronicles 1-2",
        "Day 114 - Psalm 43-45, 49, 84-85, 87",
        "Day 115 - 1 Chronicles 3-5",
        "Day 116 - Psalm 73, 77-78",
        "Day 117 - 1 Chronicles 6",
        "Day 118 - Psalm 81, 88, 92-93",
        "Day 119 - 1 Chronicles 7-10",
        "Day 120 - Psalm 102-104",
        "Day 121 - 2 Samuel 5:1-10; 1 Chronicles 11-12",
        "Day 122 - Psalm 133",
        "Day 123 - Psalm 106-107",
        "Day 124 - 2 Samuel 5:11-25; 2 Samuel 6:1-23; 1 Chronicles 13-16",
        "Day 125 - Psalm 1-2, 15, 22-24, 47, 68",
        "Day 126 - Psalm 89, 96, 100, 101, 105, 132",
        "Day 127 - 2 Samuel 7; 1 Chronicles 17",
        "Day 128 - Psalm 25, 29, 33, 36, 39",
        "Day 129 - 2 Samuel 8-9; 1 Chronicles 18",
        "Day 130 - Psalm 50, 53, 60, 75",
        "Day 131 - 2 Sam10; 1 Chronicles 19; Psalm 20",
        "Day 132 - Psalm 65-67, 69-70",
        "Day 133 - 2 Samuel 11-12; 1 Chronicles 20",
        "Day 134 - Psalm 32, 51, 86, 122",
        "Day 135 - 2 Samuel 13-15",
        "Day 136 - Psalm 3-4, 12-13, 28, 55",
        "Day 137 - 2 Samuel 16-18",
        "Day 138 - Psalm 26, 40, 58, 61-62, 64",
        "Day 139 - 2 Samuel 19-21",
        "Day 140 - Psalm 5, 38, 41-42",
        "Day 141 - 2 Samuel 22-23; Psalm 57",
        "Day 142 - Psalm 95, 97-99",
        "Day 143 - 2 Samuel 24; 1 Chronicles 21-22; Psalm 30",
        "Day 144 - Psalm 108-110",
        "Day 145 - 1 Chronicles 23-25",
        "Day 146 - Psalm 131, 138-139, 143-145",
        "Day 147 - 1 Chronicles 26-29; Psalm 127",
        "Day 148 - Psalm 111-118",
        "Day 149 - 1 Kings 1-2; Psalm 37, 71, 94",
        "Day 150 - Psalm 119:1-88",
        "Day 151 - 1 Kings 3-4; 2 Chronicles 1; Psalm 72",
        "Day 152 - Psalm 119:89-176",
        "Day 153 - Song of Solomon 1-8",
        "Day 154 - Proverbs 1-3",
        "Day 155 - Proverbs 4-6",
        "Day 156 - Proverbs 7-9",
        "Day 157 - Proverbs 10-12",
        "Day 158 - Proverbs 13-15",
        "Day 159 - Proverbs 16-18",
        "Day 160 - Proverbs 19-21",
        "Day 161 - Proverbs 22-24",
        "Day 162 - 1 Kings 5-6; 2 Chronicles 2-3",
        "Day 163 - 1 Kings 7; 2 Chronicles 4",
        "Day 164 - 1 Kings 8; 2 Chronicles 5",
        "Day 165 - 2 Chronicles 6-7; Psalm 136",
        "Day 166 - Psalm 134, 146-150",
        "Day 167 - 1 Kings 9; 2 Chronicles 8",
        "Day 168 - Proverbs 25-26",
        "Day 169 - Proverbs 27-29",
        "Day 170 - Ecclesiastes 1-6",
        "Day 171 - Ecclesiastes 7-12",
        "Day 172 - 1 Kings 10-11; 2 Chronicles 9",
        "Day 173 - Proverbs 30-31",
        "Day 174 - 1 Kings 12-14",
        "Day 175 - 2 Chronicles 10-12",
        "Day 176 - 1 Kings 15:1-24; 2 Chronicles 13-16",
        "Day 177 - 1 Kings 15:25-34; 1 Kings 16:1-34; 2 Chronicles 17",
        "Day 178 - 1 Kings 17-19",
        "Day 179 - 1 Kings 20-21",
        "Day 180 - 1 Kings 22; 2 Chronicles 18",
        "Day 181 - 2 Chronicles 19-23",
        "Day 182 - Obadiah; Psalm 82-83",
        "Day 183 - 2 Kings 1-4",
        "Day 184 - 2 Kings 5-8",
        "Day 185 - 2 Kings 9-11",
        "Day 186 - 2 Kings 12-13; 2 Chronicles 24",
        "Day 187 - 2 Kings 14; 2 Chronicles 25",
        "Day 188 - Jonah 1-4",
        "Day 189 - 2 Kings 15; 2 Chronicles 26",
        "Day 190 - Isaiah 1-4",
        "Day 191 - Isaiah 5-8",
        "Day 192 - Amos 1-5",
        "Day 193 - Amos 6-9",
        "Day 194 - 2 Chronicles 27; Isaiah 9-12",
        "Day 195 - Micah 1-7",
        "Day 196 - 2 Chronicles 28; 2 Kings 16-17",
        "Day 197 - Isaiah 13-17",
        "Day 198 - Isaiah 18-22",
        "Day 199 - Isaiah 23-27",
        "Day 200 - 2 Kings 18:1-8; 2 Chronicles 29-31; Psalm 48",
        "Day 201 - Hosea 1-7",
        "Day 202 - Hosea 8-14",
        "Day 203 - Isaiah 28-30",
        "Day 204 - Isaiah 31-34",
        "Day 205 - Isaiah 35-36",
        "Day 206 - Isaiah 37-39; Psalm 76",
        "Day 207 - Isaiah 40-43",
        "Day 208 - Isaiah 44-48",
        "Day 209 - 2 Kings 18:9-37; 2 Kings 19:1-37; Psalm 46, 80, 135",
        "Day 210 - Isaiah 49-53",
        "Day 211 - Isaiah 54-58",
        "Day 212 - Isaiah 59-63",
        "Day 213 - Isaiah 64-66",
        "Day 214 - 2 Kings 20-21",
        "Day 215 - 2 Chronicles 32-33",
        "Day 216 - Nahum 1-3",
        "Day 217 - 2 Kings 22-23; 2 Chronicles 34-35",
        "Day 218 - Zephaniah 1-3",
        "Day 219 - Jeremiah 1-3",
        "Day 220 - Jeremiah 4-6",
        "Day 221 - Jeremiah 7-9",
        "Day 222 - Jeremiah 10-13",
        "Day 223 - Jeremiah 14-17",
        "Day 224 - Jeremiah 18-22",
        "Day 225 - Jeremiah 23-25",
        "Day 226 - Jeremiah 26-29",
        "Day 227 - Jeremiah 30-31",
        "Day 228 - Jeremiah 32-34",
        "Day 229 - Jeremiah 35-37",
        "Day 230 - Jeremiah 38-40; Psalm 74, 79",
        "Day 231 - 2 Kings 24-25; 2 Chronicles 36",
        "Day 232 - Habakkuk 1-3",
        "Day 233 - Jeremiah 41-45",
        "Day 234 - Jeremiah 46-48",
        "Day 235 - Jeremiah 49-50",
        "Day 236 - Jeremiah 51-52",
        "Day 237 - Lamentations 1; Lamentations 2; Lamentations 3:1-36",
        "Day 238 - Lamentations 3:37-66; Lamentations 4; Lamentations 5:1-22",
        "Day 239 - Ezekiel 1-4",
        "Day 240 - Ezekiel 5-8",
        "Day 241 - Ezekiel 9-12",
        "Day 242 - Ezekiel 13-15",
        "Day 243 - Ezekiel 16-17",
        "Day 244 - Ezekiel 18-19",
        "Day 245 - Ezekiel 20-21",
        "Day 246 - Ezekiel 22-23",
        "Day 247 - Ezekiel 24-27",
        "Day 248 - Ezekiel 28-31",
        "Day 249 - Ezekiel 32-34",
        "Day 250 - Ezekiel 35-37",
        "Day 251 - Ezekiel 38-39",
        "Day 252 - Ezekiel 40-41",
        "Day 253 - Ezekiel 42-43",
        "Day 254 - Ezekiel 44-45",
        "Day 255 - Ezekiel 46-48",
        "Day 256 - Joel 1-3",
        "Day 257 - Daniel 1-3",
        "Day 258 - Daniel 4-6",
        "Day 259 - Daniel 7-9",
        "Day 260 - Daniel 10-12",
        "Day 261 - Ezra 1-3",
        "Day 262 - Ezra 4-6; Psalm 137",
        "Day 263 - Haggai 1-2",
        "Day 264 - Zechariah 1-7",
        "Day 265 - Zechariah 8-14",
        "Day 266 - Esther 1-5",
        "Day 267 - Esther 6-10",
        "Day 268 - Ezra 7-10",
        "Day 269 - Nehemiah 1-5",
        "Day 270 - Nehemiah 6-7",
        "Day 271 - Nehemiah 8-10",
        "Day 272 - Nehemiah 11-13; Psalm 126",
        "Day 273 - Malachi 1-4",
        "Day 274 - Luke 1; John 1:1-14",
        "Day 275 - Matthew 1; Luke 2:1-38",
        "Day 276 - Matthew 2; Luke 2:39-52",
        "Day 277 - Matthew 3; Mark 1; Luke 3",
        "Day 278 - Matthew 4; Luke 4-5; John 1:15-51",
        "Day 279 - John 2-4",
        "Day 280 - Mark 2",
        "Day 281 - John 5",
        "Day 282 - Matthew 12:1-21; Mark 3; Luke 6",
        "Day 283 - Matthew 5-7",
        "Day 284 - Matthew 8:1-13; Luke 7",
        "Day 285 - Matthew 11",
        "Day 286 - Matthew 12:22-50; Luke 11",
        "Day 287 - Matthew 13; Luke 8",
        "Day 288 - Matthew 8:14-34; Mark 4-5",
        "Day 289 - Matthew 9-10",
        "Day 290 - Matthew 14; Mark 6; Luke 9:1-17",
        "Day 291 - John 6",
        "Day 292 - Matthew 15; Mark 7",
        "Day 293 - Matthew 16; Mark 8; Luke 9:18-27",
        "Day 294 - Matthew 17; Mark 9; Luke 9:28-62",
        "Day 295 - Matthew 18",
        "Day 296 - John 7-8",
        "Day 297 - John 9:1-41; John 10:1-21",
        "Day 298 - Luke 10-11; John 10:22-42",
        "Day 299 - Luke 12-13",
        "Day 300 - Luke 14-15",
        "Day 301 - Luke 16; Luke 17:1-10",
        "Day 302 - John 11",
        "Day 303 - Luke 17:11-37; Luke 18:1-14",
        "Day 304 - Matthew 19; Mark 10",
        "Day 305 - Matthew 20-21",
        "Day 306 - Luke 18:15--43; Luke 19:1-48",
        "Day 307 - Mark 11; John 12",
        "Day 308 - Matthew 22; Mark 12",
        "Day 309 - Matthew 23; Luke 20-21",
        "Day 310 - Mark 13",
        "Day 311 - Matthew 24",
        "Day 312 - Matthew 25",
        "Day 313 - Matthew 26; Mark 14",
        "Day 314 - Luke 22; John 13",
        "Day 315 - John 14-17",
        "Day 316 - Matthew 27; Mark 15",
        "Day 317 - Luke 23; John 18-19",
        "Day 318 - Matthew 28; Mark 16",
        "Day 319 - Luke 24; John 20-21",
        "Day 320 - Acts 1-3",
        "Day 321 - Acts 4-6",
        "Day 322 - Acts 7-8",
        "Day 323 - Acts 9-10",
        "Day 324 - Acts 11-12",
        "Day 325 - Acts 13-14",
        "Day 326 - James 1-5",
        "Day 327 - Acts 15-16",
        "Day 328 - Galatians 1-3",
        "Day 329 - Galatians 4-6",
        "Day 330 - Acts 17; Acts 18:1-18",
        "Day 331 - 1 Thessalonians 1-5; 2 Thessalonians 1-3",
        "Day 332 - Acts 18:19-28; Acts 19:1-41",
        "Day 333 - 1 Corinthians 1-4",
        "Day 334 - 1 Corinthians 5-8",
        "Day 335 - 1 Corinthians 9-11",
        "Day 336 - 1 Corinthians 12-14",
        "Day 337 - 1 Corinthians 15-16",
        "Day 338 - 2 Corinthians 1-4",
        "Day 339 - 2 Corinthians 5-9",
        "Day 340 - 2 Corinthians 10-13",
        "Day 341 - Acts 20:1-3; Romans 1-3",
        "Day 342 - Romans 4-7",
        "Day 343 - Romans 8-10",
        "Day 344 - Romans 11-13",
        "Day 345 - Romans 14-16",
        "Day 346 - Acts 20:4-38; Acts 21; Acts 22; Acts 23:1-35",
        "Day 347 - Acts 24-26",
        "Day 348 - Acts 27-28",
        "Day 349 - Colossians 1-4; Philemon",
        "Day 350 - Ephesians 1-6",
        "Day 351 - Philippians 1-4",
        "Day 352 - 1 Timothy 1-6",
        "Day 353 - Titus 1-3",
        "Day 354 - 1 Peter 1-5",
        "Day 355 - Hebrews 1-6",
        "Day 356 - Hebrews 7-10",
        "Day 357 - Hebrews 11-13",
        "Day 358 - 2 Timothy 1-4",
        "Day 359 - 2 Peter 1-3; Jude",
        "Day 360 - 1 John 1-5",
        "Day 361 - 2 John; 3 John",
        "Day 362 - Revelation 1-5",
        "Day 363 - Revelation 6-11",
        "Day 364 - Revelation 12-18",
        "Day 365 - Revelation 19-22"
        //</editor-fold>
    };

    public static final String channelID = "channelID";
    public static final String channelName = "Channel Name";

    private NotificationManager mManager;
    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }
    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }
    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
    public NotificationCompat.Builder getChannelNotification() {
        //allow to open the app (main page) when the notification is touched
        Intent resultIntent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //grab the CONTROLLER VARIABLE reading_plan AND reading_day
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        SharedPreferences.Editor editor = settings.edit();

        //declare variables
        String reading_plan = settings.getString("reading_plan", null);
        int number_completion = settings.getInt("number_completion",0);
        int reading_day = settings.getInt("reading_day", 0);
        //reading_day is set to 1, so for arrays, reading_day should be subtracted 1
        reading_day--;

        if (reading_plan == null) {
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle("Today's Bible Verse")
                    .setContentText("Please set your reading plan.")
                    .setSmallIcon(R.drawable.ic_bible_english)
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent);
        }
        else if (reading_plan.equals("ninety_days")) {
            if (reading_day >= 90)
            {
                number_completion++;
                editor.putInt("number_completion", number_completion);
                editor.commit();
                return new NotificationCompat.Builder(getApplicationContext(), channelID)
                        .setContentTitle("Today's Bible Verse")
                        .setContentText("Congratulations, you completed the 90 days challenge!")
                        .setSmallIcon(R.drawable.ic_bible_english)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);
            }
            else {
                return new NotificationCompat.Builder(getApplicationContext(), channelID)
                        .setContentTitle("Today's Bible Verse")
                        .setContentText(ninety[reading_day])
                        .setSmallIcon(R.drawable.ic_bible_english)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);
            }
        }
        else if (reading_plan.equals("order_bible")) {
            if (reading_day >= 365) {
                number_completion++;
                editor.putInt("number_completion", number_completion);
                editor.commit();
                return new NotificationCompat.Builder(getApplicationContext(), channelID)
                        .setContentTitle("Today's Bible Verse")
                        .setContentText("Congratulations, you completed the one-year Bible reading!")
                        .setSmallIcon(R.drawable.ic_bible_english)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);
            }
            else {
                return new NotificationCompat.Builder(getApplicationContext(), channelID)
                        .setContentTitle("Today's Bible Verse")
                        .setContentText(bible[reading_day])
                        .setSmallIcon(R.drawable.ic_bible_english)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);
            }
        }
        else if (reading_plan.equals("order_history")) {
            if (reading_day >= 365) {
                number_completion++;
                editor.putInt("number_completion", number_completion);
                editor.commit();
                return new NotificationCompat.Builder(getApplicationContext(), channelID)
                        .setContentTitle("Today's Bible Verse")
                        .setContentText("Congratulations, you completed the one-year Bible reading!")
                        .setSmallIcon(R.drawable.ic_bible_english)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);
            }
            else {
                return new NotificationCompat.Builder(getApplicationContext(), channelID)
                        .setContentTitle("Today's Bible Verse")
                        .setContentText(history[reading_day])
                        .setSmallIcon(R.drawable.ic_bible_english)
                        .setAutoCancel(true)
                        .setContentIntent(resultPendingIntent);
            }
        }
        else {
            return new NotificationCompat.Builder(getApplicationContext(), channelID)
                    .setContentTitle("Today's Bible Verse")
                    .setContentText("Please set your Bible verse notification today!")
                    .setSmallIcon(R.drawable.ic_bible_english)
                    .setAutoCancel(true)
                    .setContentIntent(resultPendingIntent);
        }
    }
}
