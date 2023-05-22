db.createUser(
    {
        user: "book",
        pwd: "book",
        roles: [
            {
                role: "readWrite",
                db: "book-reviews"
            }
        ]
    }
);