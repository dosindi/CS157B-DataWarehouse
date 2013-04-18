SELECT state as Quantity, 
	(SELECT sum(quantity) 
		FROM forum.quantity, forum.store, forum.product
		WHERE storeID = store.id
			AND productID = product.id
			AND category = "Individual Sports"
			AND S.state = state
	) as "Individual Sports",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.store, forum.product
		WHERE storeID = store.id
			AND productID = product.id
			AND category = "Team Sports"
			AND S.state = state
	)as "Team Sports",
	(SELECT sum(quantity)
		FROM forum.quantity, forum.store, forum.product
		WHERE storeID = store.id
			AND productID = product.id
			AND category = "Water Sports"
			AND S.state = state
	)as "Water Sports"
	FROM forum.Store S
	GROUP BY state;