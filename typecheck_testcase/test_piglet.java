MAIN
PRINT CALL
	BEGIN
	MOVE TEMP 51 
		BEGIN
		MOVE TEMP 49 HALLOCATE 4
		HSTORE TEMP 49 0 BT_Start
		MOVE TEMP 50 HALLOCATE 4
		HSTORE TEMP 50 0 TEMP 49
		RETURN TEMP 50
		END
	HLOAD TEMP 52 TEMP 51 0
	HLOAD TEMP 53 TEMP 52 0
	RETURN TEMP 53
	END (TEMP 51)
END

BT_Start [ 1 ]
	BEGIN 
	MOVE TEMP 21 
		BEGIN
		MOVE TEMP 54 HALLOCATE 80
		HSTORE TEMP 54 0 Tree_GetRight
		HSTORE TEMP 54 4 Tree_GetLeft
		HSTORE TEMP 54 8 Tree_Search
		HSTORE TEMP 54 12 Tree_RemoveRight
		HSTORE TEMP 54 16 Tree_Insert
		HSTORE TEMP 54 20 Tree_SetLeft
		HSTORE TEMP 54 24 Tree_Compare
		HSTORE TEMP 54 28 Tree_Print
		HSTORE TEMP 54 32 Tree_GetHas_Left
		HSTORE TEMP 54 36 Tree_RemoveLeft
		HSTORE TEMP 54 40 Tree_SetHas_Left
		HSTORE TEMP 54 44 Tree_GetHas_Right
		HSTORE TEMP 54 48 Tree_GetKey
		HSTORE TEMP 54 52 Tree_Init
		HSTORE TEMP 54 56 Tree_SetKey
		HSTORE TEMP 54 60 Tree_RecPrint
		HSTORE TEMP 54 64 Tree_Remove
		HSTORE TEMP 54 68 Tree_Delete
		HSTORE TEMP 54 72 Tree_SetRight
		HSTORE TEMP 54 76 Tree_SetHas_Right
		MOVE TEMP 55 HALLOCATE 28
		HSTORE TEMP 55 4 0
		HSTORE TEMP 55 8 0
		HSTORE TEMP 55 12 0
		HSTORE TEMP 55 16 0
		HSTORE TEMP 55 20 0
		HSTORE TEMP 55 24 0
		HSTORE TEMP 55 0 TEMP 54
		RETURN TEMP 55
		END 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 56 TEMP 21
		HLOAD TEMP 57 TEMP 56 0
		HLOAD TEMP 58 TEMP 57 52
		RETURN TEMP 58
		END (TEMP 56 16) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 59 TEMP 21
		HLOAD TEMP 60 TEMP 59 0
		HLOAD TEMP 61 TEMP 60 28
		RETURN TEMP 61
		END (TEMP 59)
	PRINT 100000000 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 62 TEMP 21
		HLOAD TEMP 63 TEMP 62 0
		HLOAD TEMP 64 TEMP 63 16
		RETURN TEMP 64
		END (TEMP 62 8) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 65 TEMP 21
		HLOAD TEMP 66 TEMP 65 0
		HLOAD TEMP 67 TEMP 66 28
		RETURN TEMP 67
		END (TEMP 65) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 68 TEMP 21
		HLOAD TEMP 69 TEMP 68 0
		HLOAD TEMP 70 TEMP 69 16
		RETURN TEMP 70
		END (TEMP 68 24) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 71 TEMP 21
		HLOAD TEMP 72 TEMP 71 0
		HLOAD TEMP 73 TEMP 72 16
		RETURN TEMP 73
		END (TEMP 71 4) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 74 TEMP 21
		HLOAD TEMP 75 TEMP 74 0
		HLOAD TEMP 76 TEMP 75 16
		RETURN TEMP 76
		END (TEMP 74 12) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 77 TEMP 21
		HLOAD TEMP 78 TEMP 77 0
		HLOAD TEMP 79 TEMP 78 16
		RETURN TEMP 79
		END (TEMP 77 20) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 80 TEMP 21
		HLOAD TEMP 81 TEMP 80 0
		HLOAD TEMP 82 TEMP 81 16
		RETURN TEMP 82
		END (TEMP 80 28) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 83 TEMP 21
		HLOAD TEMP 84 TEMP 83 0
		HLOAD TEMP 85 TEMP 84 16
		RETURN TEMP 85
		END (TEMP 83 14) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 86 TEMP 21
		HLOAD TEMP 87 TEMP 86 0
		HLOAD TEMP 88 TEMP 87 28
		RETURN TEMP 88
		END (TEMP 86)
	PRINT CALL
		BEGIN
		MOVE TEMP 89 TEMP 21
		HLOAD TEMP 90 TEMP 89 0
		HLOAD TEMP 91 TEMP 90 8
		RETURN TEMP 91
		END (TEMP 89 24)
	PRINT CALL
		BEGIN
		MOVE TEMP 92 TEMP 21
		HLOAD TEMP 93 TEMP 92 0
		HLOAD TEMP 94 TEMP 93 8
		RETURN TEMP 94
		END (TEMP 92 12)
	PRINT CALL
		BEGIN
		MOVE TEMP 95 TEMP 21
		HLOAD TEMP 96 TEMP 95 0
		HLOAD TEMP 97 TEMP 96 8
		RETURN TEMP 97
		END (TEMP 95 16)
	PRINT CALL
		BEGIN
		MOVE TEMP 98 TEMP 21
		HLOAD TEMP 99 TEMP 98 0
		HLOAD TEMP 100 TEMP 99 8
		RETURN TEMP 100
		END (TEMP 98 50)
	PRINT CALL
		BEGIN
		MOVE TEMP 101 TEMP 21
		HLOAD TEMP 102 TEMP 101 0
		HLOAD TEMP 103 TEMP 102 8
		RETURN TEMP 103
		END (TEMP 101 12) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 104 TEMP 21
		HLOAD TEMP 105 TEMP 104 0
		HLOAD TEMP 106 TEMP 105 68
		RETURN TEMP 106
		END (TEMP 104 12) 
	MOVE TEMP 20 CALL
		BEGIN
		MOVE TEMP 107 TEMP 21
		HLOAD TEMP 108 TEMP 107 0
		HLOAD TEMP 109 TEMP 108 28
		RETURN TEMP 109
		END (TEMP 107)
	PRINT CALL
		BEGIN
		MOVE TEMP 110 TEMP 21
		HLOAD TEMP 111 TEMP 110 0
		HLOAD TEMP 112 TEMP 111 8
		RETURN TEMP 112
		END (TEMP 110 12)
	RETURN 0
	END

Tree_Init [ 2 ]
	BEGIN 
	HSTORE TEMP 0 24 TEMP 1 
	HSTORE TEMP 0 20 0 
	HSTORE TEMP 0 8 0
	RETURN 1
	END

Tree_SetRight [ 2 ]
	BEGIN 
	HSTORE TEMP 0 16 TEMP 1
	RETURN 1
	END

Tree_SetLeft [ 2 ]
	BEGIN 
	HSTORE TEMP 0 12 TEMP 1
	RETURN 1
	END

Tree_GetRight [ 1 ]
	BEGIN
	RETURN 
		BEGIN
		HLOAD TEMP 118 TEMP 0 16
		RETURN TEMP 118
		END
	END

Tree_GetLeft [ 1 ]
	BEGIN
	RETURN 
		BEGIN
		HLOAD TEMP 119 TEMP 0 12
		RETURN TEMP 119
		END
	END

Tree_GetKey [ 1 ]
	BEGIN
	RETURN 
		BEGIN
		HLOAD TEMP 120 TEMP 0 24
		RETURN TEMP 120
		END
	END

Tree_SetKey [ 2 ]
	BEGIN 
	HSTORE TEMP 0 24 TEMP 1
	RETURN 1
	END

Tree_GetHas_Right [ 1 ]
	BEGIN
	RETURN 
		BEGIN
		HLOAD TEMP 122 TEMP 0 8
		RETURN TEMP 122
		END
	END

Tree_GetHas_Left [ 1 ]
	BEGIN
	RETURN 
		BEGIN
		HLOAD TEMP 123 TEMP 0 20
		RETURN TEMP 123
		END
	END

Tree_SetHas_Left [ 2 ]
	BEGIN 
	HSTORE TEMP 0 20 TEMP 1
	RETURN 1
	END

Tree_SetHas_Right [ 2 ]
	BEGIN 
	HSTORE TEMP 0 8 TEMP 1
	RETURN 1
	END

Tree_Compare [ 3 ]
	BEGIN 
	MOVE TEMP 33 0 
	MOVE TEMP 34 PLUS TEMP 2 1
	CJUMP LT TEMP 1 TEMP 2 label_1 
	MOVE TEMP 33 0
	JUMP label_2
label_1
	CJUMP MINUS 1 LT TEMP 1 TEMP 34 label_3 
	MOVE TEMP 33 0
	JUMP label_4
label_3 
	MOVE TEMP 33 1
label_4
	NOOP
label_2
	NOOP
	RETURN TEMP 33
	END

Tree_Insert [ 2 ]
	BEGIN 
	MOVE TEMP 29 
		BEGIN
		MOVE TEMP 126 HALLOCATE 80
		HSTORE TEMP 126 0 Tree_GetRight
		HSTORE TEMP 126 4 Tree_GetLeft
		HSTORE TEMP 126 8 Tree_Search
		HSTORE TEMP 126 12 Tree_RemoveRight
		HSTORE TEMP 126 16 Tree_Insert
		HSTORE TEMP 126 20 Tree_SetLeft
		HSTORE TEMP 126 24 Tree_Compare
		HSTORE TEMP 126 28 Tree_Print
		HSTORE TEMP 126 32 Tree_GetHas_Left
		HSTORE TEMP 126 36 Tree_RemoveLeft
		HSTORE TEMP 126 40 Tree_SetHas_Left
		HSTORE TEMP 126 44 Tree_GetHas_Right
		HSTORE TEMP 126 48 Tree_GetKey
		HSTORE TEMP 126 52 Tree_Init
		HSTORE TEMP 126 56 Tree_SetKey
		HSTORE TEMP 126 60 Tree_RecPrint
		HSTORE TEMP 126 64 Tree_Remove
		HSTORE TEMP 126 68 Tree_Delete
		HSTORE TEMP 126 72 Tree_SetRight
		HSTORE TEMP 126 76 Tree_SetHas_Right
		MOVE TEMP 127 HALLOCATE 28
		HSTORE TEMP 127 4 0
		HSTORE TEMP 127 8 0
		HSTORE TEMP 127 12 0
		HSTORE TEMP 127 16 0
		HSTORE TEMP 127 20 0
		HSTORE TEMP 127 24 0
		HSTORE TEMP 127 0 TEMP 126
		RETURN TEMP 127
		END 
	MOVE TEMP 28 CALL
		BEGIN
		MOVE TEMP 128 TEMP 29
		HLOAD TEMP 129 TEMP 128 0
		HLOAD TEMP 130 TEMP 129 52
		RETURN TEMP 130
		END (TEMP 128 TEMP 1) 
	MOVE TEMP 32 TEMP 0 
	MOVE TEMP 31 1
label_5
	CJUMP TEMP 31 label_6 
	MOVE TEMP 30 CALL
		BEGIN
		MOVE TEMP 131 TEMP 32
		HLOAD TEMP 132 TEMP 131 0
		HLOAD TEMP 133 TEMP 132 48
		RETURN TEMP 133
		END (TEMP 131)
	CJUMP LT TEMP 1 TEMP 30 label_7
	CJUMP CALL
		BEGIN
		MOVE TEMP 134 TEMP 32
		HLOAD TEMP 135 TEMP 134 0
		HLOAD TEMP 136 TEMP 135 32
		RETURN TEMP 136
		END (TEMP 134) label_9 
	MOVE TEMP 32 CALL
		BEGIN
		MOVE TEMP 137 TEMP 32
		HLOAD TEMP 138 TEMP 137 0
		HLOAD TEMP 139 TEMP 138 4
		RETURN TEMP 139
		END (TEMP 137)
	JUMP label_10
label_9 
	MOVE TEMP 31 0 
	MOVE TEMP 28 CALL
		BEGIN
		MOVE TEMP 140 TEMP 32
		HLOAD TEMP 141 TEMP 140 0
		HLOAD TEMP 142 TEMP 141 40
		RETURN TEMP 142
		END (TEMP 140 1) 
	MOVE TEMP 28 CALL
		BEGIN
		MOVE TEMP 143 TEMP 32
		HLOAD TEMP 144 TEMP 143 0
		HLOAD TEMP 145 TEMP 144 20
		RETURN TEMP 145
		END (TEMP 143 TEMP 29)
label_10
	NOOP
	JUMP label_8
label_7
	CJUMP CALL
		BEGIN
		MOVE TEMP 146 TEMP 32
		HLOAD TEMP 147 TEMP 146 0
		HLOAD TEMP 148 TEMP 147 44
		RETURN TEMP 148
		END (TEMP 146) label_11 
	MOVE TEMP 32 CALL
		BEGIN
		MOVE TEMP 149 TEMP 32
		HLOAD TEMP 150 TEMP 149 0
		HLOAD TEMP 151 TEMP 150 0
		RETURN TEMP 151
		END (TEMP 149)
	JUMP label_12
label_11 
	MOVE TEMP 31 0 
	MOVE TEMP 28 CALL
		BEGIN
		MOVE TEMP 152 TEMP 32
		HLOAD TEMP 153 TEMP 152 0
		HLOAD TEMP 154 TEMP 153 76
		RETURN TEMP 154
		END (TEMP 152 1) 
	MOVE TEMP 28 CALL
		BEGIN
		MOVE TEMP 155 TEMP 32
		HLOAD TEMP 156 TEMP 155 0
		HLOAD TEMP 157 TEMP 156 72
		RETURN TEMP 157
		END (TEMP 155 TEMP 29)
label_12
	NOOP
label_8
	NOOP
	JUMP label_5
label_6
	NOOP
	RETURN 1
	END

Tree_Delete [ 2 ]
	BEGIN 
	MOVE TEMP 48 TEMP 0 
	MOVE TEMP 43 TEMP 0 
	MOVE TEMP 46 1 
	MOVE TEMP 47 0 
	MOVE TEMP 42 1
label_13
	CJUMP TEMP 46 label_14 
	MOVE TEMP 45 CALL
		BEGIN
		MOVE TEMP 158 TEMP 48
		HLOAD TEMP 159 TEMP 158 0
		HLOAD TEMP 160 TEMP 159 48
		RETURN TEMP 160
		END (TEMP 158)
	CJUMP LT TEMP 1 TEMP 45 label_15
	CJUMP CALL
		BEGIN
		MOVE TEMP 161 TEMP 48
		HLOAD TEMP 162 TEMP 161 0
		HLOAD TEMP 163 TEMP 162 32
		RETURN TEMP 163
		END (TEMP 161) label_17 
	MOVE TEMP 43 TEMP 48 
	MOVE TEMP 48 CALL
		BEGIN
		MOVE TEMP 164 TEMP 48
		HLOAD TEMP 165 TEMP 164 0
		HLOAD TEMP 166 TEMP 165 4
		RETURN TEMP 166
		END (TEMP 164)
	JUMP label_18
label_17 
	MOVE TEMP 46 0
label_18
	NOOP
	JUMP label_16
label_15
	CJUMP LT TEMP 45 TEMP 1 label_19
	CJUMP CALL
		BEGIN
		MOVE TEMP 167 TEMP 48
		HLOAD TEMP 168 TEMP 167 0
		HLOAD TEMP 169 TEMP 168 44
		RETURN TEMP 169
		END (TEMP 167) label_21 
	MOVE TEMP 43 TEMP 48 
	MOVE TEMP 48 CALL
		BEGIN
		MOVE TEMP 170 TEMP 48
		HLOAD TEMP 171 TEMP 170 0
		HLOAD TEMP 172 TEMP 171 0
		RETURN TEMP 172
		END (TEMP 170)
	JUMP label_22
label_21 
	MOVE TEMP 46 0
label_22
	NOOP
	JUMP label_20
label_19
	CJUMP TEMP 42 label_23
	CJUMP TIMES MINUS 1 CALL
		BEGIN
		MOVE TEMP 173 TEMP 48
		HLOAD TEMP 174 TEMP 173 0
		HLOAD TEMP 175 TEMP 174 44
		RETURN TEMP 175
		END (TEMP 173) MINUS 1 CALL
		BEGIN
		MOVE TEMP 176 TEMP 48
		HLOAD TEMP 177 TEMP 176 0
		HLOAD TEMP 178 TEMP 177 32
		RETURN TEMP 178
		END (TEMP 176) label_25 
	MOVE TEMP 44 1
	JUMP label_26
label_25 
	MOVE TEMP 44 CALL
		BEGIN
		MOVE TEMP 179 TEMP 0
		HLOAD TEMP 180 TEMP 179 0
		HLOAD TEMP 181 TEMP 180 64
		RETURN TEMP 181
		END (TEMP 179 TEMP 43 TEMP 48)
label_26
	NOOP
	JUMP label_24
label_23 
	MOVE TEMP 44 CALL
		BEGIN
		MOVE TEMP 182 TEMP 0
		HLOAD TEMP 183 TEMP 182 0
		HLOAD TEMP 184 TEMP 183 64
		RETURN TEMP 184
		END (TEMP 182 TEMP 43 TEMP 48)
label_24
	NOOP 
	MOVE TEMP 47 1 
	MOVE TEMP 46 0
label_20
	NOOP
label_16
	NOOP 
	MOVE TEMP 42 0
	JUMP label_13
label_14
	NOOP
	RETURN TEMP 47
	END

Tree_Remove [ 3 ]
	BEGIN
	CJUMP CALL
		BEGIN
		MOVE TEMP 185 TEMP 2
		HLOAD TEMP 186 TEMP 185 0
		HLOAD TEMP 187 TEMP 186 32
		RETURN TEMP 187
		END (TEMP 185) label_27 
	MOVE TEMP 39 CALL
		BEGIN
		MOVE TEMP 188 TEMP 0
		HLOAD TEMP 189 TEMP 188 0
		HLOAD TEMP 190 TEMP 189 36
		RETURN TEMP 190
		END (TEMP 188 TEMP 1 TEMP 2)
	JUMP label_28
label_27
	CJUMP CALL
		BEGIN
		MOVE TEMP 191 TEMP 2
		HLOAD TEMP 192 TEMP 191 0
		HLOAD TEMP 193 TEMP 192 44
		RETURN TEMP 193
		END (TEMP 191) label_29 
	MOVE TEMP 39 CALL
		BEGIN
		MOVE TEMP 194 TEMP 0
		HLOAD TEMP 195 TEMP 194 0
		HLOAD TEMP 196 TEMP 195 12
		RETURN TEMP 196
		END (TEMP 194 TEMP 1 TEMP 2)
	JUMP label_30
label_29 
	MOVE TEMP 40 CALL
		BEGIN
		MOVE TEMP 197 TEMP 2
		HLOAD TEMP 198 TEMP 197 0
		HLOAD TEMP 199 TEMP 198 48
		RETURN TEMP 199
		END (TEMP 197) 
	MOVE TEMP 41 CALL
		BEGIN
		MOVE TEMP 203 CALL
			BEGIN
			MOVE TEMP 200 TEMP 1
			HLOAD TEMP 201 TEMP 200 0
			HLOAD TEMP 202 TEMP 201 4
			RETURN TEMP 202
			END (TEMP 200)
		HLOAD TEMP 204 TEMP 203 0
		HLOAD TEMP 205 TEMP 204 48
		RETURN TEMP 205
		END (TEMP 203)
	CJUMP CALL
		BEGIN
		MOVE TEMP 206 TEMP 0
		HLOAD TEMP 207 TEMP 206 0
		HLOAD TEMP 208 TEMP 207 24
		RETURN TEMP 208
		END (TEMP 206 TEMP 40 TEMP 41) label_31 
	MOVE TEMP 39 CALL
		BEGIN
		MOVE TEMP 209 TEMP 1
		HLOAD TEMP 210 TEMP 209 0
		HLOAD TEMP 211 TEMP 210 20
		RETURN TEMP 211
		END (TEMP 209 
		BEGIN
		HLOAD TEMP 212 TEMP 0 4
		RETURN TEMP 212
		END) 
	MOVE TEMP 39 CALL
		BEGIN
		MOVE TEMP 213 TEMP 1
		HLOAD TEMP 214 TEMP 213 0
		HLOAD TEMP 215 TEMP 214 40
		RETURN TEMP 215
		END (TEMP 213 0)
	JUMP label_32
label_31 
	MOVE TEMP 39 CALL
		BEGIN
		MOVE TEMP 216 TEMP 1
		HLOAD TEMP 217 TEMP 216 0
		HLOAD TEMP 218 TEMP 217 72
		RETURN TEMP 218
		END (TEMP 216 
		BEGIN
		HLOAD TEMP 219 TEMP 0 4
		RETURN TEMP 219
		END) 
	MOVE TEMP 39 CALL
		BEGIN
		MOVE TEMP 220 TEMP 1
		HLOAD TEMP 221 TEMP 220 0
		HLOAD TEMP 222 TEMP 221 76
		RETURN TEMP 222
		END (TEMP 220 0)
label_32
	NOOP
label_30
	NOOP
label_28
	NOOP
	RETURN 1
	END

Tree_RemoveRight [ 3 ]
	BEGIN
label_33
	CJUMP CALL
		BEGIN
		MOVE TEMP 223 TEMP 2
		HLOAD TEMP 224 TEMP 223 0
		HLOAD TEMP 225 TEMP 224 44
		RETURN TEMP 225
		END (TEMP 223) label_34 
	MOVE TEMP 27 CALL
		BEGIN
		MOVE TEMP 226 TEMP 2
		HLOAD TEMP 227 TEMP 226 0
		HLOAD TEMP 228 TEMP 227 56
		RETURN TEMP 228
		END (TEMP 226 CALL
		BEGIN
		MOVE TEMP 232 CALL
			BEGIN
			MOVE TEMP 229 TEMP 2
			HLOAD TEMP 230 TEMP 229 0
			HLOAD TEMP 231 TEMP 230 0
			RETURN TEMP 231
			END (TEMP 229)
		HLOAD TEMP 233 TEMP 232 0
		HLOAD TEMP 234 TEMP 233 48
		RETURN TEMP 234
		END (TEMP 232)) 
	MOVE TEMP 1 TEMP 2 
	MOVE TEMP 2 CALL
		BEGIN
		MOVE TEMP 235 TEMP 2
		HLOAD TEMP 236 TEMP 235 0
		HLOAD TEMP 237 TEMP 236 0
		RETURN TEMP 237
		END (TEMP 235)
	JUMP label_33
label_34
	NOOP 
	MOVE TEMP 27 CALL
		BEGIN
		MOVE TEMP 238 TEMP 1
		HLOAD TEMP 239 TEMP 238 0
		HLOAD TEMP 240 TEMP 239 72
		RETURN TEMP 240
		END (TEMP 238 
		BEGIN
		HLOAD TEMP 241 TEMP 0 4
		RETURN TEMP 241
		END) 
	MOVE TEMP 27 CALL
		BEGIN
		MOVE TEMP 242 TEMP 1
		HLOAD TEMP 243 TEMP 242 0
		HLOAD TEMP 244 TEMP 243 76
		RETURN TEMP 244
		END (TEMP 242 0)
	RETURN 1
	END

Tree_RemoveLeft [ 3 ]
	BEGIN
label_35
	CJUMP CALL
		BEGIN
		MOVE TEMP 245 TEMP 2
		HLOAD TEMP 246 TEMP 245 0
		HLOAD TEMP 247 TEMP 246 32
		RETURN TEMP 247
		END (TEMP 245) label_36 
	MOVE TEMP 37 CALL
		BEGIN
		MOVE TEMP 248 TEMP 2
		HLOAD TEMP 249 TEMP 248 0
		HLOAD TEMP 250 TEMP 249 56
		RETURN TEMP 250
		END (TEMP 248 CALL
		BEGIN
		MOVE TEMP 254 CALL
			BEGIN
			MOVE TEMP 251 TEMP 2
			HLOAD TEMP 252 TEMP 251 0
			HLOAD TEMP 253 TEMP 252 4
			RETURN TEMP 253
			END (TEMP 251)
		HLOAD TEMP 255 TEMP 254 0
		HLOAD TEMP 256 TEMP 255 48
		RETURN TEMP 256
		END (TEMP 254)) 
	MOVE TEMP 1 TEMP 2 
	MOVE TEMP 2 CALL
		BEGIN
		MOVE TEMP 257 TEMP 2
		HLOAD TEMP 258 TEMP 257 0
		HLOAD TEMP 259 TEMP 258 4
		RETURN TEMP 259
		END (TEMP 257)
	JUMP label_35
label_36
	NOOP 
	MOVE TEMP 37 CALL
		BEGIN
		MOVE TEMP 260 TEMP 1
		HLOAD TEMP 261 TEMP 260 0
		HLOAD TEMP 262 TEMP 261 20
		RETURN TEMP 262
		END (TEMP 260 
		BEGIN
		HLOAD TEMP 263 TEMP 0 4
		RETURN TEMP 263
		END) 
	MOVE TEMP 37 CALL
		BEGIN
		MOVE TEMP 264 TEMP 1
		HLOAD TEMP 265 TEMP 264 0
		HLOAD TEMP 266 TEMP 265 40
		RETURN TEMP 266
		END (TEMP 264 0)
	RETURN 1
	END

Tree_Search [ 2 ]
	BEGIN 
	MOVE TEMP 26 TEMP 0 
	MOVE TEMP 25 1 
	MOVE TEMP 23 0
label_37
	CJUMP TEMP 25 label_38 
	MOVE TEMP 24 CALL
		BEGIN
		MOVE TEMP 267 TEMP 26
		HLOAD TEMP 268 TEMP 267 0
		HLOAD TEMP 269 TEMP 268 48
		RETURN TEMP 269
		END (TEMP 267)
	CJUMP LT TEMP 1 TEMP 24 label_39
	CJUMP CALL
		BEGIN
		MOVE TEMP 270 TEMP 26
		HLOAD TEMP 271 TEMP 270 0
		HLOAD TEMP 272 TEMP 271 32
		RETURN TEMP 272
		END (TEMP 270) label_41 
	MOVE TEMP 26 CALL
		BEGIN
		MOVE TEMP 273 TEMP 26
		HLOAD TEMP 274 TEMP 273 0
		HLOAD TEMP 275 TEMP 274 4
		RETURN TEMP 275
		END (TEMP 273)
	JUMP label_42
label_41 
	MOVE TEMP 25 0
label_42
	NOOP
	JUMP label_40
label_39
	CJUMP LT TEMP 24 TEMP 1 label_43
	CJUMP CALL
		BEGIN
		MOVE TEMP 276 TEMP 26
		HLOAD TEMP 277 TEMP 276 0
		HLOAD TEMP 278 TEMP 277 44
		RETURN TEMP 278
		END (TEMP 276) label_45 
	MOVE TEMP 26 CALL
		BEGIN
		MOVE TEMP 279 TEMP 26
		HLOAD TEMP 280 TEMP 279 0
		HLOAD TEMP 281 TEMP 280 0
		RETURN TEMP 281
		END (TEMP 279)
	JUMP label_46
label_45 
	MOVE TEMP 25 0
label_46
	NOOP
	JUMP label_44
label_43 
	MOVE TEMP 23 1 
	MOVE TEMP 25 0
label_44
	NOOP
label_40
	NOOP
	JUMP label_37
label_38
	NOOP
	RETURN TEMP 23
	END

Tree_Print [ 1 ]
	BEGIN 
	MOVE TEMP 36 TEMP 0 
	MOVE TEMP 35 CALL
		BEGIN
		MOVE TEMP 282 TEMP 0
		HLOAD TEMP 283 TEMP 282 0
		HLOAD TEMP 284 TEMP 283 60
		RETURN TEMP 284
		END (TEMP 282 TEMP 36)
	RETURN 1
	END

Tree_RecPrint [ 2 ]
	BEGIN
	CJUMP CALL
		BEGIN
		MOVE TEMP 285 TEMP 1
		HLOAD TEMP 286 TEMP 285 0
		HLOAD TEMP 287 TEMP 286 32
		RETURN TEMP 287
		END (TEMP 285) label_47 
	MOVE TEMP 38 CALL
		BEGIN
		MOVE TEMP 288 TEMP 0
		HLOAD TEMP 289 TEMP 288 0
		HLOAD TEMP 290 TEMP 289 60
		RETURN TEMP 290
		END (TEMP 288 CALL
		BEGIN
		MOVE TEMP 291 TEMP 1
		HLOAD TEMP 292 TEMP 291 0
		HLOAD TEMP 293 TEMP 292 4
		RETURN TEMP 293
		END (TEMP 291))
	JUMP label_48
label_47 
	MOVE TEMP 38 1
label_48
	NOOP
	PRINT CALL
		BEGIN
		MOVE TEMP 294 TEMP 1
		HLOAD TEMP 295 TEMP 294 0
		HLOAD TEMP 296 TEMP 295 48
		RETURN TEMP 296
		END (TEMP 294)
	CJUMP CALL
		BEGIN
		MOVE TEMP 297 TEMP 1
		HLOAD TEMP 298 TEMP 297 0
		HLOAD TEMP 299 TEMP 298 44
		RETURN TEMP 299
		END (TEMP 297) label_49 
	MOVE TEMP 38 CALL
		BEGIN
		MOVE TEMP 300 TEMP 0
		HLOAD TEMP 301 TEMP 300 0
		HLOAD TEMP 302 TEMP 301 60
		RETURN TEMP 302
		END (TEMP 300 CALL
		BEGIN
		MOVE TEMP 303 TEMP 1
		HLOAD TEMP 304 TEMP 303 0
		HLOAD TEMP 305 TEMP 304 0
		RETURN TEMP 305
		END (TEMP 303))
	JUMP label_50
label_49 
	MOVE TEMP 38 1
label_50
	NOOP
	RETURN 1
	END